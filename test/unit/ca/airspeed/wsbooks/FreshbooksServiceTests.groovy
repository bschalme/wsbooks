package ca.airspeed.wsbooks


import com.sun.org.apache.xalan.internal.xsltc.compiler.Import;

import com.freshbooks.model.Invoice
import java.text.SimpleDateFormat;

import grails.test.mixin.*
import org.joda.time.DateTime;
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(FreshbooksService)
class FreshbooksServiceTests {

    void testCreateInvoice() {
		def endOfLastMonth = new DateTime().withDayOfMonth(1).minusDays(1).toDate()
		def beginningOfLastMonth = new DateTime().minusMonths(1).withDayOfMonth(1).toDate()
		def startOfMonthFm = new SimpleDateFormat('MMM d')
		def endOfMonthFm = new SimpleDateFormat('d, yyyy')
        def inv = [:]
		def line = [:]
		def lines = []

		inv.invoiceNumber = '1'
		inv.sourceSystem = 'QuickBooks'
		inv.clientID = 'CB0000-1190817876'
		inv.date = endOfLastMonth
		inv.terms = 'Net 30'
		def expectedOther = startOfMonthFm.format(beginningOfLastMonth) + ' - '  + endOfMonthFm.format(endOfLastMonth)
		inv.period = expectedOther
		inv.notes = 'Airspeed Consulting is a division of 4020774 Manitoba Ltd.'
		line.name = 'A&P:$100.00/hr'
		line.description = 'Analysis & Programming Services'
		line.quantity = 2.5
		line.unitCost = 100.00
		def taxes = []
		def tax = [:]
		tax.name = 'GST'
		tax.percent = new BigDecimal('5.00')
		taxes << tax
		line.taxes = taxes
		lines << line
		inv.lines = lines
		
		def fbInv = service.createInvoice(inv)
		
		assert fbInv?.number == '1'
		assert fbInv?.clientId == 2
		assert fbInv?.status == 'draft'
		assert fbInv.date == endOfLastMonth
		assert fbInv.notes == 'For services for the period ' + inv.period + '\n' + inv.notes
		assert fbInv.terms == inv.terms
		assert fbInv.currencyCode == 'CAD'
		assert fbInv.lines.size() == 1
		
		def fbLine = fbInv.lines[0]
		assert fbLine.name == 'A&P-1'
		assert fbLine.description == line.description
		assert fbLine.quantity == line.quantity
		assert fbLine.unitCost == line.unitCost
		assert fbLine.tax1Name == 'GST'
		assert fbLine.tax1Percent == '5.00'
		
    }
}
