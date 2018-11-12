<!doctype html>
<html>
	<head>
		<meta name="layout" content="bootstrap"/>
		<title>WS-Books</title>
	</head>

	<body>
		<div class="row-fluid">
			<aside id="application-status" class="span3">
				<div class="well sidebar-nav">
					<h5>Application Status</h5>
					<ul>
						<li>App version: <g:meta name="app.version"/></li>
						<li>Grails version: <g:meta name="app.grails.version"/></li>
						<li>Groovy version: ${groovy.lang.GroovySystem.getVersion()}</li>
						<li>JVM version: ${System.getProperty('java.version')}</li>
						<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
						<li>Domains: ${grailsApplication.domainClasses.size()}</li>
						<li>Services: ${grailsApplication.serviceClasses.size()}</li>
						<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
					</ul>
					<h5>Installed Plugins</h5>
					<ul>
						<g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
							<li>${plugin.name} - ${plugin.version}</li>
						</g:each>
					</ul>
				</div>
			</aside>

			<section id="main" class="span9">

				<div class="hero-unit">
					<h1>Welcome to WS-Books</h1>

                    <p>WS-Books captures yesterday's timesheets from TSheets and feeds
                       them into QuickBooks. On a monthly basis it generates
                       time-and-materials invoices and posts them to QuickBooks and to
                       Freshbooks for delivery.</p>

                    <p>Use this application to manage the control information, job
                       schedules and the various cross-reference tables.</p>
				</div>
					
				<div class="row-fluid">
					
					<div class="span4">
						<h2>Manage WS-Books</h2>
						<p>View and change cross-reference tables and manage the scheduled Quartz jobs</p>
						<ul class="nav nav-list">
                            <li class="controller"><g:link controller="control">Control</g:link></li>
                            <li class="controller"><g:link controller="quartz">Quartz Jobs</g:link></li>
                            <li class="controller"><g:link controller="tsheetsJobcodeXref">TSheets Job Cross-Reference</g:link></li>
                            <li class="controller"><g:link controller="tsheetsUserXref">TSheets User Cross-Reference</g:link></li>
                            <li class="controller"><g:link controller="recurrence">Recurring Items</g:link></li>
						</ul>
					</div>

					<div class="span4">
						<h2>Install WS-Books</h2>
						<p>This is a Grails application. To get going make sure you are running the following:</p>
						<p>Grails 2.3.7. Grab it from the <a href="https://grails.org/download.html" target="_blank">Grails Framework Download</a> page.
						   Check the version you have by going:</p>
						<pre>grails -version</pre>
						<p>Java 1.7. Ensure your JAVA_HOME environment variable is pointing to your JDK home.
						   I haven&apos;t tested this yet with Java 8.</p>
						<p>Build it with the standard Grails commands:</p>
						<pre>grails test-app
grails run-app
grails war</pre>
                        <p>Take that WAR file and deploy it to a Tomcat 7.0 servlet container.</p>
					</div>
					
					<div class="span4">
						<h2>Fork WS-Books</h2>
						<p>You can download, fork &amp; raise issues on this project on <a href="https://github.com/bschalme/wsbooks">GitHub</a>.</p>
					</div>

				</div>

			</section>
		</div>
		
		<a href="https://github.com/bschalme/wsbooks"><img id="github-ribbon" src="https://camo.githubusercontent.com/e7bbb0521b397edbd5fe43e7f760759336b5e05f/68747470733a2f2f73332e616d617a6f6e6177732e636f6d2f6769746875622f726962626f6e732f666f726b6d655f72696768745f677265656e5f3030373230302e706e67" alt="Fork me on GitHub" data-canonical-src="https://s3.amazonaws.com/github/ribbons/forkme_right_green_007200.png"></a>
	</body>
</html>
