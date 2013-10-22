<%@ page import="ca.airspeed.wsbooks.Control" %>



<div class="fieldcontain ${hasErrors(bean: controlInstance, field: 'tsheetsLastFetchedDate', 'error')} required">
	<label for="tsheetsLastFetchedDate">
		<g:message code="control.tsheetsLastFetchedDate.label" default="Tsheets Last Fetched Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="tsheetsLastFetchedDate" precision="day"  value="${controlInstance?.tsheetsLastFetchedDate}"  />
</div>

