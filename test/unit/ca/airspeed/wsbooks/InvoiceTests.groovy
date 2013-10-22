package ca.airspeed.wsbooks



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Invoice)
class InvoiceTests {

    void testConstraints() {
       def inv = new Invoice()
	   
	   assert !inv.validate()
	   assert "nullable" == inv.errors["txnID"].code
	   assert "missing.customer" == inv.errors["customerRefListID"].code
	   assert "nullable" == inv.errors["txnDate"].code
	   assert "nullable" == inv.errors["refNumber"].code
	   assert "missing.terms" == inv.errors["termsRefListID"].code
	   
	   inv.txnID = 'ABC-123'
	   inv.customerRefFullName = "MegaCorp:Real Estate Web Service"
	   inv.txnDate = new Date()
	   inv.refNumber = '99999'
	   inv.termsRefFullName = 'Net 30'
	   inv.status = 'ZZZZ'
	   assert !inv.validate()
	   assert "not.inList" == inv.errors["status"].code
	   
	   inv.status = 'ADD'
	   assert inv.validate()
	   inv.status = 'UPDATE'
	   assert inv.validate()
	   inv.status = 'DELETE'
	   assert inv.validate()
    }
	
}
