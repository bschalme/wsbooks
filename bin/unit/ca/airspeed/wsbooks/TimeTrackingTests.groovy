package ca.airspeed.wsbooks

import grails.test.mixin.TestFor;

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TimeTracking)
class TimeTrackingTests {

    void testConstraints() {
       def t = new TimeTracking()
	   t.status = 'BLAH'
	   assert !t.validate()
	   assert "nullable" == t.errors["txnID"].code
	   assert "nullable" == t.errors["txnDate"].code
	   assert "nullable" == t.errors["entityRefListID"].code
	   assert "nullable" == t.errors["customer"].code
	   assert "nullable" == t.errors["itemService"]?.code
	   assert "nullable" == t.errors["duration"].code
	   assert "not.inList" == t.errors["status"].code
	   
	   t.status = 'ADD'
	   t.txnID = 'ABC-123'
	   t.txnDate = new Date()
	   t.entityRefListID = 'EMP-1'
	   t.customer = new Customer(name: 'CUST-1')
	   t.itemService = new ItemService(name: 'ITEM-1')
	   t.duration = 'X9XHR8'
	   assert !t.validate()
	   assert "matches.invalid" == t.errors["duration"]?.code
	   
	   t.duration = 'PT0H30M'
	   t.status = ''
	   assert t.validate()
    }

	void testGetDurationInMinutes() {
		def ts = new TimeTracking()
		
		ts.duration = 'PT0H30M'
		assert ts.durationInMinutes == 30
		
		ts.duration = 'PT1H0M'
		assert ts.durationInMinutes == 60
		
		ts.duration ='PT1H30M'
		assert ts.durationInMinutes == 90
	}
	
	void testSetDurationInMinutes() {
		def ts = new TimeTracking()
		
		ts.durationInMinutes = 120
		assert ts.duration == 'PT2H0M'
		
		ts.durationInMinutes = 90
		assert ts.duration == 'PT1H30M'
		
		ts.durationInMinutes = 30
		assert ts.duration == 'PT0H30M'
	}
}
