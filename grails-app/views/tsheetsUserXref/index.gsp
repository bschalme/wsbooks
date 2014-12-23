
<%@ page import="ca.airspeed.wsbooks.TsheetsUserXref" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tsheetsUserXref.label', default: 'TsheetsUserXref')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-tsheetsUserXref" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-tsheetsUserXref" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="userName" title="${message(code: 'tsheetsUserXref.userName.label', default: 'User Name')}" />
					
						<g:sortableColumn property="qbEntityListId" title="${message(code: 'tsheetsUserXref.qbEntityListId.label', default: 'Qb Entity List Id')}" />
					
						<g:sortableColumn property="tsheetsUserId" title="${message(code: 'tsheetsUserXref.tsheetsUserId.label', default: 'Tsheets User Id')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tsheetsUserXrefInstanceList}" status="i" var="tsheetsUserXrefInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tsheetsUserXrefInstance.id}">${fieldValue(bean: tsheetsUserXrefInstance, field: "userName")}</g:link></td>
					
						<td>${fieldValue(bean: tsheetsUserXrefInstance, field: "qbEntityListId")}</td>
					
						<td>${fieldValue(bean: tsheetsUserXrefInstance, field: "tsheetsUserId")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tsheetsUserXrefInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
