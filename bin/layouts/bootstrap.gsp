<%@ page import="org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes" %>
<!doctype html>
<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="${meta(name: 'app.name')}"/></title>
		<meta name="description" content="">
		<meta name="author" content="">

		<meta name="viewport" content="width=device-width, initial-scale=1.0">

		<!-- Le HTML5 shim, for IE6-8 support of HTML elements -->
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

		<r:require modules="scaffolding"/>

		<!-- Le fav and touch icons -->
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">

		<g:layoutHead/>
		<r:layoutResources/>
	</head>

	<body>
		<nav class="navbar navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container-fluid">
					
					<a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					
					<a class="brand" href="${createLink(uri: '/')}">WS-Books</a>

					<div class="nav-collapse">
						<ul class="nav">							
							<li<%= request.forwardURI == "${createLink(uri: '/')}" ? ' class="active"' : '' %>><a href="${createLink(uri: '/')}">Home</a></li>
							<g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                                <g:if test="${c.getStaticPropertyValue('linkMe', Boolean) || c.fullName == 'grails.plugins.quartz.QuartzController'}">
								<li<%= c.logicalPropertyName == controllerName ? ' class="active"' : '' %>>
									<g:link controller="${c.logicalPropertyName}">
										<g:message code="${c.name}.label" default="${c.naturalName}" />
									</g:link>
								</li>
								</g:if>
							</g:each>
							<sec:ifLoggedIn>
								<li>
									<g:remoteLink class="logout" controller="logout" method="post" asynchronous="false" onSuccess="location.replace(${createLink(uri: '/')})">
										<g:message code="Logout.label" default="Logout" />
									</g:remoteLink>
								</li>
							</sec:ifLoggedIn>
							<sec:ifAllGranted roles='ROLE_ADMIN'>
								<li>
									<g:link controller="User">
										<g:message code="security.admin.label" default="Sec Admin" />
									</g:link>
								</li>
							</sec:ifAllGranted>
						</ul>
					</div>
				</div>
			</div>
		</nav>

		<div class="container-fluid">
			<g:layoutBody/>

			<hr>

			<footer>
				<p>&copy; <a href="https://airspeed.ca" target="_blank" title="Website of Airspeed Consulting">Airspeed Consulting</a> 2015</p>
			</footer>
		</div>

		<r:layoutResources/>

	</body>
</html>