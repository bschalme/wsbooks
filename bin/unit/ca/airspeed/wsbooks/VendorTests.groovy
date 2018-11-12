
package ca.airspeed.wsbooks

import grails.test.mixin.TestFor

@TestFor(Vendor)
class VendorTests {

	void testConstraints() {
		def vendor = new Vendor()
		assert !vendor.validate()
		assert "nullable" == vendor.errors["listID"].code
		assert "nullable" == vendor.errors["name"].code

		vendor.listID = 'ABC-456'
		assert !vendor.validate()
		assert "nullable" == vendor.errors["name"].code

		vendor.status = 'XXX'
		assert !vendor.validate()
		assert "not.inList" == vendor.errors["status"].code

		vendor.status = null
		assert !vendor.validate()

		vendor.name = 'MegaCorp'
		vendor.status = 'UPDATE'
		vendor.isActive = 'true'
		assert vendor.validate()
	}
}
