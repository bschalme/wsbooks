package ca.airspeed.wsbooks;

import grails.test.mixin.TestFor;

@TestFor(Items)
class ItemsTests {

	void testConstraints() {
		Items items = new Items()
		
		assert !items.validate()
		assert "nullable" == items.errors["listID"].code
		assert "nullable" == items.errors["fullName"].code
		assert "nullable" == items.errors["tableName"].code
		
		items.listID = 'ABC-123'
		items.fullName = 'Alphabet Soup'
		items.tableName = 'customer'
		
		assert !items.validate()
		assert "not.inList" == items.errors["tableName"].code
		
		items.tableName = 'itemservice'
		assert items.validate()
	} 

}
