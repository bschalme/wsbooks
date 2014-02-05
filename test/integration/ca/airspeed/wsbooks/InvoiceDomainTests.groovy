package ca.airspeed.wsbooks;

import static org.junit.Assert.*;

import java.awt.geom.Line2D;

import org.junit.Test;

class InvoiceDomainTests {

	@Test
	public void testSave() {
		def inv = new Invoice()
		inv.txnID = 'ABC-123'
		inv.customerRefFullName = "MegaCorp:Real Estate Web Service"
		inv.txnDate = new Date()
		inv.refNumber = '99999'
		inv.termsRefFullName = 'Net 30'
		inv.status = 'ADD'
		InvoiceLineDetail invLine = new InvoiceLineDetail()
		invLine.txnLineID = "DEF-456"
		invLine.itemRefListID = "QRS-789"
		invLine.quantity = 2
		inv.addToDetailLines(invLine)
		
		inv.save(flush: true, failOnError: true)
		
		assert Invoice.list().size() == 1
		inv = Invoice.list().get(0)
		assert inv.detailLines?.size() == 1
		for (line in inv.detailLines) {
			assert line.txnLineID == 'DEF-456'
			assert line.invoice.txnID == 'ABC-123'
		}
	}

}
