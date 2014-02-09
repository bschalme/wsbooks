package ca.airspeed.wsbooks



import grails.test.mixin.TestFor

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(InvoiceLineDetail)
class InvoiceLineDetailTests {

    void testConstraints() {
       def dtl = new InvoiceLineDetail()
	   
	   assert !dtl.validate()
	   assert "nullable" == dtl.errors["txnLineID"].code
	   assert "nullable" == dtl.errors["item"].code
	   // assert "nullable" == dtl.errors["description"]?.code
	   assert "nullable" == dtl.errors["quantity"].code
	   // assert "nullable" == dtl.errors["rate"].code
	   // assert "missing.sales.tax.code" == dtl.errors["salesTaxCodeRefListID"].code
	   
	   dtl.txnLineID = "DEF-456"
	   dtl.item = new Items(fullName: 'A&P:$100/hr', listID: '140000-1069940598')
	   dtl.description = ''
	   dtl.quantity = '23A'
	   dtl.salesTaxCodeRefFullName = 'G'
	   assert !dtl.validate()
	   assert "notANumber" == dtl.errors["quantity"].code
	   
	   dtl.quantity = '-0.1'
	   assert !dtl.validate()
	   assert "min.notmet" == dtl.errors["quantity"].code
	   
	   dtl.quantity= '125.5'
	   dtl.rate = '100'
	   dtl.description = 'Analysis & Programming Services'
	   assert !dtl.validate()
	   assert "nullable" == dtl.errors["invoice"].code
	   
	   dtl.invoice = new Invoice()
	   assert dtl.validate()
    }
}
