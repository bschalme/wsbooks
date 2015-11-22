# WS-Books

Updates QuickBooks with yesterday's TSheets timesheets. In additionn, each month it generates an invoice for the previous month, and sends it to FreshBooks for delivery to your clients. 

## What Problem Does It Solve?

WS-Books solves two problems:

1. Repetitive recording of time worked; and
2. Time spent generating invoices each month.

In the days before WS-Books day I would jot down my time on paper for each client, noting the start and stop time and a sentence or two describing what I did. At the end of the day I would enter this same information into QuickBooks (the desktop edition). It was annoying having to record the same information twice, once on paper and again on a computer.

Then on the first of the month I would go into QuickBooks and create invoices - one for each client I worked for the previous month. For each invoice I had to:

1. Create it, telling QuickBooks to pull in all the unbilled time entries.
2. Generate a PDF version of the invoice
3. Send an email to the client containing the invoice.

Not a big deal for just one client. But for three or more it was a time-consuming process. Never mind following up on overdue invoices.

WS-Books takes care of all this. All I need to do is enter my timesheets once into TSheets. I usually enter them as I start and stop various activities through the day. I make sure I have the day's time entered before I retire for the evening.

## Setup

This assumes you already have the following:

1. A [TSheets](https://www.tsheets.com/) account;
2. A [FreshBooks](http://www.freshbooks.com/) account; and
3. A MySQL database schema generated by Synergration Software's [OpenSync](http://synergration.com/software/opensync/)

### Environment Variables

Since this is an integration solution, there are a number of environment variables you need to have in place. For TSheets:

```
TSHEETS_REST_URL="https://rest.tsheets.com/api/v1/"
TSHEETS_REST_TOKEN=<Your TSheets Access Token>
```

For FreshBooks:
```
FRESHBOOKS_URL=<Your FreshBooks URL>
FRESHBOOKS_TOKEN=<Your FreshBooks Access Token>
```

WS-Books uses two databases. One for the cross-reference tables, and runtime control data:
```
DB_WSBOOKS_HOST=<Database host name or IP address>
DB_WSBOOKS_PORT=3306
DB_WSBOOKS_USERNAME=<The username you designate for this database>
DB_WSBOOKS_PASSWORD=<Its password>
```

And the other for the OpenSync database:
```
DB_QUICKBOOKS_HOST=<Database host name or IP address>
DB_QUICKBOOKS_PORT=3306
DB_QUICKBOOKS_USERNAME=<The username you designated for updating the OpenSync database.>
DB_QUICKBOOKS_PASSWORD=<Its password>
```

### Configuration Files

Create the file `wsbooks-config.groovy`. For development put it in the `.grails` folder of your home directory; for Production put it on Tomcat's classpath:

```Groovy
// PROD
import org.apache.log4j.DailyRollingFileAppender
import org.apache.log4j.net.SMTPAppender
import org.apache.log4j.Level

// log4j configuration
// Set driveLetter to 'C:', 'D:', whatever. Disregard it when running on *NIX boxes.
def driveLetter = System.getProperty('os.name').startsWith('Windows') ? 'D:' : ''
log4j = {
	appenders {
		appender new DailyRollingFileAppender(
			name: 'stdout',
			file: driveLetter + "/apps/logs/${grails.util.Metadata.current.getApplicationName()}.log",
			datePattern: "'.'yyyy-MM-dd",
			layout:pattern(conversionPattern: '%d %p %c{10} - %m%n')
		)
		appender new SMTPAppender(
			name: 'email',
			SMTPHost: 'yourmailhost',
			SMTPUsername: 'smptuser',
			SMTPPassword: 'secret',
			from: 'info@domain.com',
			to: 'you@domain.com', // Your email address
			subject: 'WS-Books Notification', // Change this if you like
			layout: pattern(conversionPattern: '%d{yyyy-MM-dd HH:mm} - %m%n'),
			evaluatorClass: 'ca.airspeed.wsbooks.EndOfJobEvaluator',
			threshold: Level.INFO,
			SMTPDebug: false
		)
	}

	error  'org.codehaus.groovy.grails.web.servlet',        // controllers
		   'org.codehaus.groovy.grails.web.pages',          // GSP
		   'org.codehaus.groovy.grails.web.sitemesh',       // layouts
		   'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
		   'org.codehaus.groovy.grails.web.mapping',        // URL mapping
		   'org.codehaus.groovy.grails.commons',            // core / classloading
		   'org.codehaus.groovy.grails.plugins',            // plugins
		   'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
		   'org.springframework',
		   'org.hibernate',
		   'net.sf.ehcache.hibernate'
		   
	info   'grails.app.services.ca.airspeed.wsbooks.SampleService', 'stdout', 'email',
	       'grails.app', 'stdout'
	
	root {
		error 'stdout', 'email'
		additivity = true
	}
}
```

Finally, create a `wsbooks-quartz-config.groovy` file in the same directory as you did `wsbooks-config.groovy`. This contains the cron expressions for the various jobs that WS-Books runs:

```Groovy
quartzJobs {
	jobs = [
		'FetchTimesheetsJob': [
			cronTriggers: [
				cronExpression: '0 15 6 * * ?'
			]
		],
		'WorkInProcessJob': [
			cronTriggers: [
				cronExpression: '0 20 6 * * ?'
			]
		],
		'MonthlyInvoiceJob': [
			cronTriggers: [
				cronExpression: '0 25 6 1 * ?'
			]
		],
		'EndOfScheduleJob': [ // Just something that triggers the email to go out.
			cronTriggers: [
				cronExpression: '0 30 6 * * ?'
			]
		]
	]
}
```

Make sure `EndOfScheduleJob` is the last Job to run. It sends an email to you summarizing what it did.

## Building

WS-Books uses Grails 2.3.7 and Java 1.7. Ensure you have the environment variable `JAVA_HOME` pointing to a JDK 1,7 installations.

The usual Grails commands apply:

```
grails test-app
grails run-app
grails war
```

## Deploying

Deploy the WAR file you created with `grails war` into Tomcat. Browse to http://localhost:8080/wsbooks-<version>, where `<version>` is the version number suffix on the WAR file name.

If you want to be able to browse to a simple URL like [http://localhost:8080/wsbooks](http://localhost:8080/wsbooks), put the WAR file and this `wsbooks.xml` deployment descriptor in a directory outside of Tomcat:

```XML
<?xml version='1.0' encoding='utf-8'?>
<Context path="/wsbooks" docBase="D:/webapps/wsbooks-0.13.0.war"/>
```

Replace `D:/webapps/` with the directory path you chose.

Now deply it using Tomcat's deplyment manager.
