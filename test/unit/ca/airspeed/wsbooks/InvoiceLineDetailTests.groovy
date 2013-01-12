package ca.airspeed.wsbooks



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(InvoiceLineDetail)
class InvoiceLineDetailTests {

    void testConstraints() {
       def dtl = new InvoiceLineDetail()
	   
	   assert !dtl.validate()
	   assert "nullable" == dtl.errors["txnLineID"].code
	   assert "nullable" == dtl.errors["iDKEY"].code
	   assert "missing.item" == dtl.errors["itemRefListID"].code
	   assert "nullable" == dtl.errors["description"].code
	   assert "nullable" == dtl.errors["quantity"].code
	   assert "nullable" == dtl.errors["rate"].code
	   assert "missing.sales.tax.code" == dtl.errors["salesTaxCodeRefListID"].code
	   
	   dtl.txnLineID = "DEF-456"
	   dtl.iDKEY = "ABC-123"
	   dtl.itemRefFullName = 'A&P:$100/hr'
	   dtl.description = ''
	   dtl.quantity = '23A'
	   dtl.rate = 'A23'
	   dtl.salesTaxCodeRefFullName = 'G'
	   assert !dtl.validate()
	   assert "blank" == dtl.errors["description"].code
	   assert "notANumber" == dtl.errors["quantity"].code
	   assert "notANumber" == dtl.errors["rate"].code
	   
	   dtl.quantity = '-0.1'
	   dtl.rate = '-5'
	   assert !dtl.validate()
	   assert "min.notmet" == dtl.errors["quantity"].code
	   assert "min.notmet" == dtl.errors["rate"].code
	   
	   dtl.quantity= '125.5'
	   dtl.rate = '100'
	   dtl.description = 'Analysis & Programming Services'
	   assert dtl.validate()
    }
}
