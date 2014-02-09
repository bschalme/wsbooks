package ca.airspeed.wsbooks;

import static org.junit.Assert.*;

import java.awt.geom.Line2D;

import org.junit.Test;

class InvoiceDomainTests {

	@Test
	public void testSave() {
		def inv = new Invoice()
		inv.txnID = 'ABC-123'
		inv.arAccount = Account.get('3F0000-930012744')
		inv.customer = Customer.get('CB0000-1190817876')
		inv.txnDate = new Date()
		inv.refNumber = '99999'
		inv.terms = StandardTerms.get('20000-929918818')
		inv.status = 'ADD'
		InvoiceLineDetail invLine = new InvoiceLineDetail()
		invLine.txnLineID = "DEF-456"
		invLine.item = Items.get('140000-1069940598')
		invLine.quantity = 2
		inv.addToDetailLines(invLine)
		
		inv.save(flush: true, failOnError: true)
		
		assert Invoice.list().size() == 1
		inv = Invoice.list().get(0)
		assert inv.arAccount.name == 'Accounts Receivable'
		assert inv.terms.name == 'Net 30'
		assert inv.refNumber == '99999'
		assert inv.detailLines?.size() == 1
		for (line in inv.detailLines) {
			assert line.txnLineID == 'DEF-456'
			assert line.item.fullName == 'A&P:$100.00/hr'
			assert line.invoice.txnID == 'ABC-123'
		}
	}

}
