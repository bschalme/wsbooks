package ca.airspeed.wsbooks



import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Invoice)
class InvoiceTests {

    void testConstraints() {
       def inv = new Invoice()
	   
	   assert !inv.validate()
	   assert "nullable" == inv.errors["txnID"].code
	   assert "nullable" == inv.errors["customer"].code
	   assert "nullable" == inv.errors["txnDate"].code
	   assert "nullable" == inv.errors["refNumber"].code
	   assert "nullable" == inv.errors["arAccount"].code
	   assert "missing.terms" == inv.errors["termsRefListID"].code
	   
	   inv.txnID = 'ABC-123'
	   inv.arAccount = new Account()
	   inv.customer = new Customer(name: "MegaCorp:Real Estate Web Service")
	   inv.txnDate = new Date()
	   inv.refNumber = '99999'
	   inv.customerMsg = new CustomerMsg()
	   inv.termsRefFullName = 'Net 30'
	   inv.status = 'ZZZZ'
	   assert !inv.validate()
	   assert "not.inList" == inv.errors["status"].code
	   assert "account.must.be.ar" == inv.errors["arAccount"].code
	   
	   assert "nullable" == inv.errors["detailLines"]?.find{ it.code == 'nullable'}.code
	   Set<InvoiceLineDetail> details = new HashSet<InvoiceLineDetail>()
	   inv.detailLines = details
	   inv.arAccount.accountType = 'AccountsReceivable'
	   assert !inv.validate()
	   assert "invoice.details.minimum" == inv.errors["detailLines"]?.find{ it.code == 'invoice.details.minimum'}?.code
	   details.add(new InvoiceLineDetail())
	   inv.detailLines = details
	   
	   inv.status = 'ADD'
	   assert inv.validate()
	   inv.status = 'UPDATE'
	   assert inv.validate()
	   inv.status = 'DELETE'
	   assert inv.validate()
    }
	
}
