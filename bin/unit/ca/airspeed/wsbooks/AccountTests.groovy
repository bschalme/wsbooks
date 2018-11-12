package ca.airspeed.wsbooks;

import grails.test.mixin.TestFor

@TestFor(Account)
class AccountTests {

	void testConstraints() {
		def account = new Account()
		
		assert !account.validate()
		assert "nullable" == account.errors["listID"].code
		assert "nullable" == account.errors["name"].code
		assert "nullable" == account.errors["fullName"].code
		assert "nullable" == account.errors["isActive"].code
		assert "nullable" == account.errors["accountType"].code
		assert "nullable" == account.errors["status"].code
		
		account.listID = 'ABC-123'
		account.name = 'TestAccount'
		account.fullName = 'TestAccount Full Name'
		account.isActive = 'maybe'
		account.accountType = 'SillyAccount'
		account.status = 'UPDATE'
		
		assert !account.validate()
		assert "not.inList" == account.errors["isActive"].code
		assert "not.inList" == account.errors["accountType"].code
		assert "not.inList" == account.errors["status"].code
		
		account.isActive = 'true'
		account.accountType = 'Income'
		account.status = 'ADD'
		
		assert account.validate()
	}
}
