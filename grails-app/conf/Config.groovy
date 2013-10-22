import static org.quartz.JobBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
import static org.quartz.TriggerBuilder.*;

import java.text.SimpleDateFormat;

import grails.plugin.quartz2.ClosureJob;
import grails.plugin.quartz2.InvokeMethodJob
import org.joda.time.DateTime
import org.quartz.Job
import org.quartz.JobDataMap
import org.quartz.JobDetail
import org.quartz.Trigger

import ca.airspeed.wsbooks.Customer;

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

grails.config.locations = [ "classpath:${appName}-config.properties",
                            "classpath:${appName}-config.groovy",
                            "file:${userHome}/.grails/${appName}-config.properties",
                            "file:${userHome}/.grails/${appName}-config.groovy"]
grails.config.locations << [ "file:/apps/conf/${appName}-config.properties",
	                         "file:D:/apps/conf/${appName}-config.groovy"]
grails.config.locations << [ "file:/apps/conf/${appName}-quartz-config.groovy",
                            "file:${userHome}/.grails/${appName}-quartz-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml']
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// Scheduled jobs
grails.plugin.quartz2.autoStartup = true

org{
	quartz{
		//anything here will get merged into the quartz.properties so you don't need another file
		scheduler.instanceName = 'WsBooksScheduler'
		threadPool.class = 'org.quartz.simpl.SimpleThreadPool'
		threadPool.threadCount = 20
		threadPool.threadsInheritContextClassLoaderOfInitializingThread = true
		jobStore.class = 'org.quartz.simpl.RAMJobStore'
	}
}

grails.plugin.quartz2.jobSetup.statusCheck = { quartzScheduler, ctx ->
	def props = new JobDataMap([targetObject:ctx.sampleService,targetMethod:'checkStatus'])
	JobDetail statusJobDetail = newJob(InvokeMethodJob.class) //use the static helper newJob from org.quartz.JobBuilder
		.withIdentity("Status Check")
		.usingJobData(props)
		.build()
	
	def statusTrigger = newTrigger().withIdentity("Status Trigger")
		.withSchedule(dailyAtHourAndMinute(00, 5))
		.startNow()
		.build()
		
	// quartzScheduler.scheduleJob(statusJobDetail, statusTrigger)
}

grails.plugin.quartz2.jobSetup.fetchFromTsheets = { quartzScheduler, ctx ->
	def props = new JobDataMap([targetObject:ctx.timesheetService,targetMethod:'fetchTimesheetsFromTsheets'])
	JobDetail jobDetail = newJob(InvokeMethodJob.class) //use the static helper newJob from org.quartz.JobBuilder
		.withIdentity("Fetch Timesheets")
		.usingJobData(props)
		.build()

	Trigger trigger = newTrigger().withIdentity("Timesheet Trigger")
		.withSchedule(
			// simpleSchedule().withIntervalInSeconds(1).repeatForever()
			dailyAtHourAndMinute(00, 15)
		)
		.startNow().build()

	quartzScheduler.scheduleJob(jobDetail, trigger)
}

grails.plugin.quartz2.jobSetup.doSomeWork = { quartzScheduler, ctx ->
	def props = new JobDataMap([targetObject:ctx.sampleService,targetMethod:'doSomeWork'])
	JobDetail jobDetail = newJob(InvokeMethodJob.class) //use the static helper newJob from org.quartz.JobBuilder
		.withIdentity("take the ride")
		.usingJobData(props)
		.build()

	Trigger trigger = newTrigger().withIdentity("hunter trigger")
		.withSchedule(
			// simpleSchedule().withIntervalInSeconds(1).repeatForever()
			dailyAtHourAndMinute(04, 20)
		)
		.startNow().build()

	quartzScheduler.scheduleJob(jobDetail, trigger)
}

grails.plugin.quartz2.jobSetup.testInvoice = { quartzScheduler, ctx ->
	def testInvoiceJob = ClosureJob.createJob(name:'Test_Invoice_Job', durability:true, concurrent:false) { jobCtx, appCtx ->
        def inv = [:]
		def line = [:]
		def lines = []

		def endOfLastMonth = new DateTime().withDayOfMonth(1).minusDays(1).toDate()
		def beginningOfLastMonth = new DateTime().minusMonths(1).withDayOfMonth(1).toDate()
		def startOfMonthFm = new SimpleDateFormat('MMM d')
		def endOfMonthFm = new SimpleDateFormat('d, yyyy')

		inv.invoiceNumber = '7'
		inv.sourceSystem = 'QuickBooks'
		inv.clientID = 'CB0000-1190817876'
		inv.date = endOfLastMonth
		inv.terms = 'Net 30'
		def expectedOther = startOfMonthFm.format(beginningOfLastMonth) + ' - '  + endOfMonthFm.format(endOfLastMonth)
		inv.period = expectedOther
		inv.notes = 'Airspeed Consulting is a division of 4020774 Manitoba Ltd.'
		line.name = 'A&P:$100.00/hr'
		line.description = 'Analysis & Programming Services'
		line.quantity = 2.5
		line.unitCost = 100.00
		def taxes = []
		def tax = [:]
		tax.name = 'GST'
		tax.percent = 5.00
		taxes << tax
		line.taxes = taxes
		lines << line
		inv.lines = lines
		if (inv?.invoiceNumber != null) {
			def fbInv = appCtx.freshbooksService.createInvoice(inv)
			def fbInvId = appCtx.freshbooksApiConn.createInvoice(fbInv)
			println 'fbInvId = ' + fbInvId
			appCtx.freshbooksApiConn.sendInvoiceByEmail(fbInvId)
		}
	}
	
	def testInvoiceTrigger = newTrigger().withIdentity("Test Invoice Trigger")
		.withSchedule(monthlyOnDayAndHourAndMinute(1, 20, 52))
		.startNow()
		.build()
		
	// quartzScheduler.scheduleJob(testInvoiceJob, testInvoiceTrigger)
}

grails.plugin.quartz2.jobSetup.monthlyInvoice = { quartzScheduler, ctx ->
	def invoiceJob = ClosureJob.createJob(name:'Monthly_Invoice_Job', durability:true, concurrent:false) { jobCtx, appCtx ->
		def activeCusts = Customer.findAllByIsActive("true")
		def endOfLastMonth = new DateTime().withDayOfMonth(1).minusDays(1).toDate()
		activeCusts.each { cust ->
			def inv = appCtx.invoiceService.createTimeByCustomerAndDate(cust, endOfLastMonth)
			if (inv?.invoiceNumber != null) {
				def fbInv = appCtx.freshbooksService.createInvoice(inv)
				def fbInvId = appCtx.freshbooksApiConn.createInvoice(fbInv)
				println 'fbInvId = ' + fbInvId
				appCtx.freshbooksApiConn.sendInvoiceByEmail(fbInvId)
			}
		}
	}
	
	def invoiceTrigger = newTrigger().withIdentity("Invoice Trigger")
		.withSchedule(monthlyOnDayAndHourAndMinute(1, 4, 25))
		.startNow()
		.build()
		
	quartzScheduler.scheduleJob(invoiceJob, invoiceTrigger)
}

grails.plugin.quartz2.jobSetup.logEndOfJob = { quartzScheduler, ctx ->
	def props = new JobDataMap([targetObject:ctx.sampleService,targetMethod:'logEndOfJob'])
	JobDetail eojJobDetail = newJob(InvokeMethodJob.class) //use the static helper newJob from org.quartz.JobBuilder
		.withIdentity("Log the end of job")
		.usingJobData(props)
		.build()

	Trigger eojTrigger = newTrigger().withIdentity("End of job trigger")
		.withSchedule(
			// simpleSchedule().withIntervalInSeconds(1).repeatForever()
			dailyAtHourAndMinute(04, 30)
		)
		.startNow().build()

	quartzScheduler.scheduleJob(eojJobDetail, eojTrigger)
}

