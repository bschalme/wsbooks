
<%@ page import="ca.airspeed.wsbooks.Recurrence" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'recurrence.label', default: 'Recurrence')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-recurrence" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-recurrence" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list recurrence">
			
				<g:if test="${recurrenceInstance?.nickname}">
				<li class="fieldcontain">
					<span id="nickname-label" class="property-label"><g:message code="recurrence.nickname.label" default="Nickname" /></span>
					
						<span class="property-value" aria-labelledby="nickname-label"><g:fieldValue bean="${recurrenceInstance}" field="nickname"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recurrenceInstance?.entityName}">
				<li class="fieldcontain">
					<span id="entityName-label" class="property-label"><g:message code="recurrence.entityName.label" default="Entity Name" /></span>
					
						<span class="property-value" aria-labelledby="entityName-label"><g:fieldValue bean="${recurrenceInstance}" field="entityName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recurrenceInstance?.entityNumber}">
				<li class="fieldcontain">
					<span id="entityNumber-label" class="property-label"><g:message code="recurrence.entityNumber.label" default="Entity Number" /></span>
					
						<span class="property-value" aria-labelledby="entityNumber-label"><g:fieldValue bean="${recurrenceInstance}" field="entityNumber"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recurrenceInstance?.frequency}">
				<li class="fieldcontain">
					<span id="frequency-label" class="property-label"><g:message code="recurrence.frequency.label" default="Frequency" /></span>
					
						<span class="property-value" aria-labelledby="frequency-label"><g:fieldValue bean="${recurrenceInstance}" field="frequency"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recurrenceInstance?.txnTiming}">
				<li class="fieldcontain">
					<span id="txnTiming-label" class="property-label"><g:message code="recurrence.txnTiming.label" default="Txn Timing" /></span>
					
						<span class="property-value" aria-labelledby="txnTiming-label"><g:fieldValue bean="${recurrenceInstance}" field="txnTiming"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${recurrenceInstance?.previousRunDate}">
				<li class="fieldcontain">
					<span id="previousRunDate-label" class="property-label"><g:message code="recurrence.previousRunDate.label" default="Previous Run Date" /></span>
					
						<span class="property-value" aria-labelledby="previousRunDate-label"><g:formatDate date="${recurrenceInstance?.previousRunDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${recurrenceInstance?.nextRunDate}">
				<li class="fieldcontain">
					<span id="nextRunDate-label" class="property-label"><g:message code="recurrence.nextRunDate.label" default="Next Run Date" /></span>
					
						<span class="property-value" aria-labelledby="nextRunDate-label"><g:formatDate date="${recurrenceInstance?.nextRunDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${recurrenceInstance?.finalRunDate}">
				<li class="fieldcontain">
					<span id="finalRunDate-label" class="property-label"><g:message code="recurrence.finalRunDate.label" default="Final Run Date" /></span>
					
						<span class="property-value" aria-labelledby="finalRunDate-label"><g:formatDate date="${recurrenceInstance?.finalRunDate}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${recurrenceInstance?.active}">
				<li class="fieldcontain">
					<span id="active-label" class="property-label"><g:message code="recurrence.active.label" default="Active" /></span>
					
						<span class="property-value" aria-labelledby="active-label"><g:formatBoolean boolean="${recurrenceInstance?.active}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${recurrenceInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="recurrence.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${recurrenceInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${recurrenceInstance?.lastUpdated}">
				<li class="fieldcontain">
					<span id="lastUpdated-label" class="property-label"><g:message code="recurrence.lastUpdated.label" default="Last Updated" /></span>
					
						<span class="property-value" aria-labelledby="lastUpdated-label"><g:formatDate date="${recurrenceInstance?.lastUpdated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${recurrenceInstance?.id}" />
					<g:link class="edit" action="edit" id="${recurrenceInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
