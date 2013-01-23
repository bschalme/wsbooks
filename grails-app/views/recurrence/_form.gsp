<%@ page import="ca.airspeed.wsbooks.Recurrence" %>



<div class="fieldcontain ${hasErrors(bean: recurrenceInstance, field: 'nickname', 'error')} required">
	<label for="nickname">
		<g:message code="recurrence.nickname.label" default="Nickname" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nickname" required="" value="${recurrenceInstance?.nickname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recurrenceInstance, field: 'entityName', 'error')} ">
	<label for="entityName">
		<g:message code="recurrence.entityName.label" default="Entity Name" />
		
	</label>
	<g:select name="entityName" from="${recurrenceInstance.constraints.entityName.inList}" value="${recurrenceInstance?.entityName}" valueMessagePrefix="recurrence.entityName" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recurrenceInstance, field: 'entityNumber', 'error')} required">
	<label for="entityNumber">
		<g:message code="recurrence.entityNumber.label" default="Entity Number" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="entityNumber" required="" value="${recurrenceInstance?.entityNumber}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recurrenceInstance, field: 'frequency', 'error')} ">
	<label for="frequency">
		<g:message code="recurrence.frequency.label" default="Frequency" />
		
	</label>
	<g:select name="frequency" from="${recurrenceInstance.constraints.frequency.inList}" value="${recurrenceInstance?.frequency}" valueMessagePrefix="recurrence.frequency" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recurrenceInstance, field: 'txnTiming', 'error')} ">
	<label for="txnTiming">
		<g:message code="recurrence.txnTiming.label" default="Txn Timing" />
		
	</label>
	<g:select name="txnTiming" from="${recurrenceInstance.constraints.txnTiming.inList}" value="${recurrenceInstance?.txnTiming}" valueMessagePrefix="recurrence.txnTiming" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: recurrenceInstance, field: 'previousRunDate', 'error')} ">
	<label for="previousRunDate">
		<g:message code="recurrence.previousRunDate.label" default="Previous Run Date" />
		
	</label>
	${recurrenceInstance?.previousRunDate?.toString()}
</div>

<div class="fieldcontain ${hasErrors(bean: recurrenceInstance, field: 'nextRunDate', 'error')} required">
	<label for="nextRunDate">
		<g:message code="recurrence.nextRunDate.label" default="Next Run Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="nextRunDate" precision="day"  value="${recurrenceInstance?.nextRunDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: recurrenceInstance, field: 'finalRunDate', 'error')} required">
	<label for="finalRunDate">
		<g:message code="recurrence.finalRunDate.label" default="Final Run Date" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="finalRunDate" precision="day"  value="${recurrenceInstance?.finalRunDate}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: recurrenceInstance, field: 'active', 'error')} ">
	<label for="active">
		<g:message code="recurrence.active.label" default="Active" />
		
	</label>
	<g:checkBox name="active" value="${recurrenceInstance?.active}" />
</div>

<div class="fieldcontain ${hasErrors(bean: recurrenceInstance, field: 'dateUpdated', 'error')} required">
	<label for="dateUpdated">
		<g:message code="recurrence.dateUpdated.label" default="Date Updated" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="dateUpdated" precision="day"  value="${recurrenceInstance?.dateUpdated}"  />
</div>

