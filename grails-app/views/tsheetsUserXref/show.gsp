
<%@ page import="ca.airspeed.wsbooks.TsheetsUserXref" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tsheetsUserXref.label', default: 'TsheetsUserXref')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tsheetsUserXref" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tsheetsUserXref" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tsheetsUserXref">
			
				<g:if test="${tsheetsUserXrefInstance?.userName}">
				<li class="fieldcontain">
					<span id="userName-label" class="property-label"><g:message code="tsheetsUserXref.userName.label" default="User Name" /></span>
					
						<span class="property-value" aria-labelledby="userName-label"><g:fieldValue bean="${tsheetsUserXrefInstance}" field="userName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tsheetsUserXrefInstance?.qbEntityListId}">
				<li class="fieldcontain">
					<span id="qbEntityListId-label" class="property-label"><g:message code="tsheetsUserXref.qbEntityListId.label" default="Qb Entity List Id" /></span>
					
						<span class="property-value" aria-labelledby="qbEntityListId-label"><g:fieldValue bean="${tsheetsUserXrefInstance}" field="qbEntityListId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tsheetsUserXrefInstance?.tsheetsUserId}">
				<li class="fieldcontain">
					<span id="tsheetsUserId-label" class="property-label"><g:message code="tsheetsUserXref.tsheetsUserId.label" default="Tsheets User Id" /></span>
					
						<span class="property-value" aria-labelledby="tsheetsUserId-label"><g:fieldValue bean="${tsheetsUserXrefInstance}" field="tsheetsUserId"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:tsheetsUserXrefInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${tsheetsUserXrefInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
