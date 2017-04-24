// PROD
import org.apache.log4j.DailyRollingFileAppender
import org.apache.log4j.net.SMTPAppender
import org.apache.log4j.Level

// log4j configuration
// Set driveLetter to 'C:', 'D:', whatever.
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
			SMTPHost: System.getenv('APP_LOGGING_SMTP_HOST'),
			SMTPUsername: System.getenv('APP_LOGGING_SMTP_USERNAME'),
			SMTPPassword: System.getenv('APP_LOGGING_SMTP_PASSWORD'),
			from: 'info@domain.com',
			to: 'sysadmin@domain.com',
			subject: 'WS-Books Notification',
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

