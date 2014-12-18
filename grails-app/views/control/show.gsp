
<%@ page import="ca.airspeed.wsbooks.Control" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'control.label', default: 'Control')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-control" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-control" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list control">
			
				<g:if test="${controlInstance?.rowName}">
				<li class="fieldcontain">
					<span id="rowName-label" class="property-label"><g:message code="control.rowName.label" default="Row Name" /></span>
					
						<span class="property-value" aria-labelledby="rowName-label"><g:fieldValue bean="${controlInstance}" field="rowName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${controlInstance?.tsheetsLastFetchedDate}">
				<li class="fieldcontain">
					<span id="tsheetsLastFetchedDate-label" class="property-label"><g:message code="control.tsheetsLastFetchedDate.label" default="Tsheets Last Fetched Date" /></span>
					
						<span class="property-value" aria-labelledby="tsheetsLastFetchedDate-label"><g:formatDate format="EEE MMM dd, yyyy" date="${controlInstance?.tsheetsLastFetchedDate}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:controlInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${controlInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
