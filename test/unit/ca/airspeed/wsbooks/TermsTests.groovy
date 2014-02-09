package ca.airspeed.wsbooks;

import grails.test.mixin.TestFor;

@TestFor(Terms)
class TermsTests {

	void testConstraints() {
		Terms tr = new Terms()

		assert !tr.validate()
		assert "nullable" == tr.errors["listID"].code
		assert "nullable" == tr.errors["fullName"].code

		tr.listID = 'ABC-123'
		tr.fullName = 'Net 37'

		assert tr.validate()
	}
}
