package ca.airspeed.wsbooks

import org.apache.commons.validator.Msg;

import grails.test.mixin.TestFor;

@TestFor(CustomerMsg)
class CustomerMsgTests {

	void testConstraints() {
		CustomerMsg msg =  new CustomerMsg()

		assert !msg.validate()
		assert "nullable" == msg.errors["listID"].code
		assert "nullable" == msg.errors["name"].code
		assert "nullable" == msg.errors["isActive"].code
		
		msg.listID = 'ABC-123'
		msg.name = 'Test Message'
		msg.isActive = 'maybe'
		msg.status = 'ADD'
		assert !msg.validate()
		assert "not.inList" == msg.errors["isActive"].code
		assert "not.inList" == msg.errors["status"].code
		
		msg.isActive = 'true'
		msg.status = null
		
		assert msg.validate()
	}
}
