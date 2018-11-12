package ca.airspeed.wsbooks



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Customer)
class CustomerTests {

    void testConstraints() {
       def cust = new Customer()
	   assert !cust.validate()
	   assert "nullable" == cust.errors["listID"].code
	   assert "nullable" == cust.errors["name"].code
	   
	   cust.listID = 'ABC-456'
	   assert !cust.validate()
	   assert "nullable" == cust.errors["name"].code
	   
	   cust.status = 'XXX'
	   assert !cust.validate()
	   assert "not.inList" == cust.errors["status"].code
	   
	   cust.status = null
	   assert !cust.validate()
	   
	   cust.name = 'MegaCorp'
	   cust.status = 'UPDATE'
	   assert cust.validate()
    }
}
