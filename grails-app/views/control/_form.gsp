<%@ page import="ca.airspeed.wsbooks.Control" %>



<div class="fieldcontain ${hasErrors(bean: controlInstance, field: 'rowName', 'error')} required">
	<label for="rowName">
		<g:message code="control.rowName.label" default="Row Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="rowName" readonly="readonly" required="" value="${controlInstance?.rowName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: controlInstance, field: 'tsheetsLastFetchedDate', 'error')} required">
	<label for="tsheetsLastFetchedDate">
		<g:message code="control.tsheetsLastFetchedDate.label" default="Tsheets Last Fetched Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="tsheetsLastFetchedDate" format="EEE MMM dd, yyyy" precision="day"  value="${controlInstance?.tsheetsLastFetchedDate}"  />

</div>

