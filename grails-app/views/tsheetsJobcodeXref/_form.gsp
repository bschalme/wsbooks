<%@ page import="ca.airspeed.wsbooks.TsheetsJobcodeXref" %>



<div class="fieldcontain ${hasErrors(bean: tsheetsJobcodeXrefInstance, field: 'jobName', 'error')} required">
	<label for="jobName">
		<g:message code="tsheetsJobcodeXref.jobName.label" default="Job Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="jobName" required="" value="${tsheetsJobcodeXrefInstance?.jobName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tsheetsJobcodeXrefInstance, field: 'qbCustomerListId', 'error')} required">
	<label for="qbCustomerListId">
		<g:message code="tsheetsJobcodeXref.qbCustomerListId.label" default="Qb Customer List Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="qbCustomerListId" required="" value="${tsheetsJobcodeXrefInstance?.qbCustomerListId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tsheetsJobcodeXrefInstance, field: 'qbItemServiceListId', 'error')} required">
	<label for="qbItemServiceListId">
		<g:message code="tsheetsJobcodeXref.qbItemServiceListId.label" default="Qb Item Service List Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="qbItemServiceListId" required="" value="${tsheetsJobcodeXrefInstance?.qbItemServiceListId}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: tsheetsJobcodeXrefInstance, field: 'tsheetsJobcodeId', 'error')} required">
	<label for="tsheetsJobcodeId">
		<g:message code="tsheetsJobcodeXref.tsheetsJobcodeId.label" default="Tsheets Jobcode Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="tsheetsJobcodeId" type="number" value="${tsheetsJobcodeXrefInstance.tsheetsJobcodeId}" required=""/>

</div>

