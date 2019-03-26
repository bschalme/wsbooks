//import static org.quartz.JobBuilder.*;
//import static org.quartz.CronScheduleBuilder.*;
//import static org.quartz.SimpleScheduleBuilder.*;
//import static org.quartz.TriggerBuilder.*;

import java.text.SimpleDateFormat;

//import org.quartz.Job
//import org.quartz.JobDataMap
//import org.quartz.JobDetail
//import org.quartz.Trigger

//import ca.airspeed.wsbooks.Customer;

// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

grails.config.locations = [ "classpath:${appName}-config.groovy",
                            "classpath:${appName}-quartz-config.groovy",
                            "file:${userHome}/.grails/${appName}-config.properties",
                            "file:${userHome}/.grails/${appName}-config.groovy",
                            "file:/apps/conf/${appName}-config.properties",
	                        "file:/apps/conf/${appName}-config.groovy",
                            "file:/apps/conf/${appName}-quartz-config.groovy",
                            "file:${userHome}/.grails/${appName}-quartz-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = 'ca.airspeed.wsbooks'
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
grails.resources.adhoc.patterns = ['/assets/**', '/images/*', '/css/*', '/js/*', '/plugins/**']
grails.resources.adhoc.includes = ['/assets/**', '/images/*', '/css/*', '/js/*', '/plugins/**']
grails.resources.adhoc.excludes = ['**/WEB-INF/**','**/META-INF/**']

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

grails.plugins.twitterbootstrap.fixtaglib = true
grails.plugins.twitterbootstrap.defaultBundle = 'bundle_bootstrap'

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
        grails.plugin.springsecurity.ipRestrictions = [
		    // Change this to your suit your situation:
           '/**': ['184.67.171.182/32', '205.200.64.3/32']
        ]
	}
}



// Uncomment and edit the following lines to start using Grails encoding & escaping improvements

/* remove this line 
// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside null
                scriptlet = 'none' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
remove this line */

quartz.monitor.layout='bootstrap'
quartz.monitor.showCountdown=false

jetty.host='0.0.0.0'

tsheets.rest.url = System.getenv('TSHEETS_REST_URL')
tsheets.rest.token = System.getenv('TSHEETS_REST_TOKEN')

freshbooks.url = System.getenv('FRESHBOOKS_URL')
freshbooks.token = System.getenv('FRESHBOOKS_TOKEN')

// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'ca.airspeed.wsbooks.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'ca.airspeed.wsbooks.UserRole'
grails.plugin.springsecurity.authority.className = 'ca.airspeed.wsbooks.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':                ['permitAll'],
	'/index':           ['permitAll'],
	'/index.gsp':       ['permitAll'],
	'/assets/**':       ['permitAll'],
	'/**/js/**':        ['permitAll'],
	'/**/css/**':       ['permitAll'],
	'/**/images/**':    ['permitAll'],
	'/**/favicon.ico':  ['permitAll'],
    '/register/**':     ['permitAll'],
    '/user/**':         ['ROLE_ADMIN'],
    '/role/**':         ['ROLE_ADMIN'],
    '/securityInfo/**':         ['ROLE_ADMIN'],
    '/registrationCode/**':         ['ROLE_ADMIN'],
]

grails.plugin.springsecurity.logout.postOnly = false
grails.plugin.springsecurity.filterChain.chainMap = [
	//Stateless chain
	'/api/**': 'JOINED_FILTERS,-anonymousAuthenticationFilter,-exceptionTranslationFilter,-authenticationProcessingFilter,-securityContextPersistenceFilter,-rememberMeAuthenticationFilter',
	//Traditional chain
    '/**': 'JOINED_FILTERS,-restTokenValidationFilter,-restExceptionTranslationFilter'
]

// From the former DataSource.groovy:
dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
dataSources {
    opensync {
        dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
        url = "jdbc:h2:mem:devOpensyncDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
    }
		}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            // url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            dialect = org.hibernate.dialect.MySQL5InnoDBDialect
            driverClassName = 'com.mysql.jdbc.Driver'
            url = "jdbc:mysql://localhost/"
            username = 'wsbooks'
            password = 'wsbooks'
            properties {
                defaultCatalog="wsbooks"
            }
        }
		dataSources {
            opensync {
                dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
                url = "jdbc:h2:mem:devOpensyncDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
            }
		}
    }
    test {
        dataSource {
            dbCreate = "create-drop"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
        }
		dataSources {
            opensync {
                dbCreate = "create-drop"
                url = "jdbc:h2:mem:testOpensyncDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
            }
		}
    }
    production {
        dataSource {
            dbCreate = "update"
            dialect = org.hibernate.dialect.MySQL5InnoDBDialect
            driverClassName = 'com.mysql.jdbc.Driver'
            String host = System.getenv('DB_WSBOOKS_HOST')
            String port = System.getenv('DB_WSBOOKS_PORT')
            url = "jdbc:mysql://$host:$port/wsbooks"
            username = System.getenv('DB_WSBOOKS_USERNAME')
            password = System.getenv('DB_WSBOOKS_PASSWORD')
            pooled = true
            properties {
               defaultCatalog="wsbooks"
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
        dataSource_opensync {
            dbCreate = "validate"
            dialect = org.hibernate.dialect.MySQLInnoDBDialect
            driverClassName = 'com.mysql.jdbc.Driver'
            String host = System.getenv('DB_QUICKBOOKS_HOST')
            String port = System.getenv('DB_QUICKBOOKS_PORT')
            url = "jdbc:mysql://$host:$port/qbairspeed"
            username = System.getenv('DB_QUICKBOOKS_USERNAME')
            password = System.getenv('DB_QUICKBOOKS_PASSWORD')
            pooled = true
            properties {
               defaultCatalog="qbairspeed"
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
}
