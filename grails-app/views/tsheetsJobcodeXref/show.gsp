
<%@ page import="ca.airspeed.wsbooks.TsheetsJobcodeXref" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tsheetsJobcodeXref" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tsheetsJobcodeXref" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tsheetsJobcodeXref">
			
				<g:if test="${tsheetsJobcodeXrefInstance?.jobName}">
				<li class="fieldcontain">
					<span id="jobName-label" class="property-label"><g:message code="tsheetsJobcodeXref.jobName.label" default="Job Name" /></span>
					
						<span class="property-value" aria-labelledby="jobName-label"><g:fieldValue bean="${tsheetsJobcodeXrefInstance}" field="jobName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tsheetsJobcodeXrefInstance?.qbCustomerListId}">
				<li class="fieldcontain">
					<span id="qbCustomerListId-label" class="property-label"><g:message code="tsheetsJobcodeXref.qbCustomerListId.label" default="Qb Customer List Id" /></span>
					
						<span class="property-value" aria-labelledby="qbCustomerListId-label"><g:fieldValue bean="${tsheetsJobcodeXrefInstance}" field="qbCustomerListId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tsheetsJobcodeXrefInstance?.qbItemServiceListId}">
				<li class="fieldcontain">
					<span id="qbItemServiceListId-label" class="property-label"><g:message code="tsheetsJobcodeXref.qbItemServiceListId.label" default="Qb Item Service List Id" /></span>
					
						<span class="property-value" aria-labelledby="qbItemServiceListId-label"><g:fieldValue bean="${tsheetsJobcodeXrefInstance}" field="qbItemServiceListId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${tsheetsJobcodeXrefInstance?.tsheetsJobcodeId}">
				<li class="fieldcontain">
					<span id="tsheetsJobcodeId-label" class="property-label"><g:message code="tsheetsJobcodeXref.tsheetsJobcodeId.label" default="Tsheets Jobcode Id" /></span>
					
						<span class="property-value" aria-labelledby="tsheetsJobcodeId-label"><g:fieldValue bean="${tsheetsJobcodeXrefInstance}" field="tsheetsJobcodeId"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:tsheetsJobcodeXrefInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${tsheetsJobcodeXrefInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
