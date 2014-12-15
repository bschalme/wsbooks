grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // specify dependency exclusions here; for example, uncomment this to disable ehcache:
        // excludes 'ehcache'
		// excludes 'grails-plugin-log4j', 'log4j', 'commons-logging'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins

        grailsPlugins()
        grailsHome()
        grailsCentral()

        mavenLocal()
        mavenCentral()

        // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        compile 'mysql:mysql-connector-java:5.1.23'
		compile 'joda-time:joda-time:2.1'
		runtime 'javax.mail:mail:1.4.7'
		runtime 'javax.activation:activation:1.1.1'
		compile 'com.freshbooks:freshbooksApiClient:1.0-SNAPSHOT'
		// compile 'org.grails.plugins:logback:0.3.1'
		// compile 'org.slf4j:slf4j-api:1.7.5'
		// compile 'org.slf4j:jcl-over-slf4j:1.7.5'
		// compile 'org.slf4j:log4j-over-slf4j:1.7.5'
		// compile 'org.slf4j:slf4j-nop:1.7.5'
		// compile 'ch.qos.logback:logback-classic:1.0.13'
		// compile 'ch.qos.logback:logback-core:1.0.13'
		// compile 'org.logback-extensions:logback-ext-spring:0.1.1'
		// compile 'com.google.guava:guava:14.0.1'
    }

    plugins {
        runtime ":hibernate4:4.3.4.1"
        runtime ":jquery:1.8.0"
        runtime ":resources:1.2.7"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

        build ":tomcat:7.0.52.1"

        runtime ":database-migration:1.3.8"

        compile ':cache:1.1.8'
		compile ":quartz:1.0.2"
		compile ":quartz-monitor:1.0"
		compile ":rest-client-builder:1.0.3"
    }
	
}
