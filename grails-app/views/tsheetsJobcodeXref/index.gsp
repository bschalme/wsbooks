
<%@ page import="ca.airspeed.wsbooks.TsheetsJobcodeXref" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tsheetsJobcodeXref" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tsheetsJobcodeXref" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="jobName" title="${message(code: 'tsheetsJobcodeXref.jobName.label', default: 'Job Name')}" />
					
						<g:sortableColumn property="qbCustomerListId" title="${message(code: 'tsheetsJobcodeXref.qbCustomerListId.label', default: 'Qb Customer List Id')}" />
					
						<g:sortableColumn property="qbItemServiceListId" title="${message(code: 'tsheetsJobcodeXref.qbItemServiceListId.label', default: 'Qb Item Service List Id')}" />
					
						<g:sortableColumn property="tsheetsJobcodeId" title="${message(code: 'tsheetsJobcodeXref.tsheetsJobcodeId.label', default: 'Tsheets Jobcode Id')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tsheetsJobcodeXrefInstanceList}" status="i" var="tsheetsJobcodeXrefInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tsheetsJobcodeXrefInstance.id}">${fieldValue(bean: tsheetsJobcodeXrefInstance, field: "jobName")}</g:link></td>
					
						<td>${fieldValue(bean: tsheetsJobcodeXrefInstance, field: "qbCustomerListId")}</td>
					
						<td>${fieldValue(bean: tsheetsJobcodeXrefInstance, field: "qbItemServiceListId")}</td>
					
						<td>${fieldValue(bean: tsheetsJobcodeXrefInstance, field: "tsheetsJobcodeId")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tsheetsJobcodeXrefInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
