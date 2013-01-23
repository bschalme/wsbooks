
<%@ page import="ca.airspeed.wsbooks.Recurrence" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'recurrence.label', default: 'Recurrence')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-recurrence" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-recurrence" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nickname" title="${message(code: 'recurrence.nickname.label', default: 'Nickname')}" />
					
						<g:sortableColumn property="entityName" title="${message(code: 'recurrence.entityName.label', default: 'Entity Name')}" />
					
						<g:sortableColumn property="entityNumber" title="${message(code: 'recurrence.entityNumber.label', default: 'Entity Number')}" />
					
						<g:sortableColumn property="frequency" title="${message(code: 'recurrence.frequency.label', default: 'Frequency')}" />
					
						<g:sortableColumn property="txnTiming" title="${message(code: 'recurrence.txnTiming.label', default: 'Txn Timing')}" />
					
						<g:sortableColumn property="previousRunDate" title="${message(code: 'recurrence.previousRunDate.label', default: 'Previous Run Date')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${recurrenceInstanceList}" status="i" var="recurrenceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${recurrenceInstance.id}">${fieldValue(bean: recurrenceInstance, field: "nickname")}</g:link></td>
					
						<td>${fieldValue(bean: recurrenceInstance, field: "entityName")}</td>
					
						<td>${fieldValue(bean: recurrenceInstance, field: "entityNumber")}</td>
					
						<td>${fieldValue(bean: recurrenceInstance, field: "frequency")}</td>
					
						<td>${fieldValue(bean: recurrenceInstance, field: "txnTiming")}</td>
					
						<td><g:formatDate date="${recurrenceInstance.previousRunDate}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${recurrenceInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
