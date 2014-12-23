<%@ page import="ca.airspeed.wsbooks.TsheetsUserXref" %>



<div class="fieldcontain ${hasErrors(bean: tsheetsUserXrefInstance, field: 'userName', 'error')} required">
	<label for="userName">
		<g:message code="tsheetsUserXref.userName.label" default="User Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="userName" required="" value="${tsheetsUserXrefInstance?.userName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tsheetsUserXrefInstance, field: 'qbEntityListId', 'error')} required">
	<label for="qbEntityListId">
		<g:message code="tsheetsUserXref.qbEntityListId.label" default="Qb Entity List Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="qbEntityListId" required="" value="${tsheetsUserXrefInstance?.qbEntityListId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tsheetsUserXrefInstance, field: 'tsheetsUserId', 'error')} required">
	<label for="tsheetsUserId">
		<g:message code="tsheetsUserXref.tsheetsUserId.label" default="Tsheets User Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tsheetsUserId" type="number" value="${tsheetsUserXrefInstance.tsheetsUserId}" required=""/>

</div>

