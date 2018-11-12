package ca.airspeed.wsbooks


import com.freshbooks.model.Invoice
import java.text.SimpleDateFormat;

import grails.test.mixin.TestFor
import java.time.ZonedDateTime;
import java.time.LocalDate;
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(FreshbooksService)
class FreshbooksServiceTests {

    void testCreateInvoice() {
		def endOfLastMonth = new LocalDate().withDayOfMonth(1).minusDays(1).toDate()
		def beginningOfLastMonth = new LocalDate().minusMonths(1).withDayOfMonth(1).toDate()
		def startOfMonthFm = new SimpleDateFormat('MMM d')
		def endOfMonthFm = new SimpleDateFormat('d, yyyy')
		def invoice = simpleInvoice()

		def fbInv = service.createInvoice(invoice)
		
		assert fbInv?.number == '1'
		assert fbInv?.clientId == 2
		assert fbInv?.status == 'draft'
		assert fbInv.date == new LocalDate().withDayOfMonth(1).minusDays(1).toDate()
		def expectedOther = startOfMonthFm.format(beginningOfLastMonth) + ' - '  + endOfMonthFm.format(endOfLastMonth)
		assert fbInv.notes == 'For services for the period ' + expectedOther + '\n' + 'Airspeed Consulting is a division of 4020774 Manitoba Ltd.'
		assert fbInv.terms == 'Net 30'
		assert fbInv.currencyCode == 'CAD'
		assert fbInv.lines.size() == 1
		
		def fbLine = fbInv.lines[0]
		assert fbLine.name == 'A&P-1'
		assert fbLine.description == 'Analysis & Programming Services'
		assert fbLine.quantity == 2.5
		assert fbLine.unitCost == 100.00
		assert fbLine.tax1Name == 'GST'
		assert fbLine.tax1Percent == '5.00'
		
    }

	void testCreateMultiLineInvoice() {
		def invoice = simpleInvoice()
		def line2 = [:]
		line2.name = 'A&P:$97.75/hr'
		line2.description = 'Analysis & Programming Services'
		line2.quantity = 5
		line2.unitCost = 97.75
		line2.taxes = invoice.lines[0].taxes
		invoice.lines << line2

		def fbInv = service.createInvoice(invoice)

		assert fbInv.lines.size() == 2
		def fbLine = fbInv.lines[0]
		assert fbLine.name == 'A&P-1'
		assert fbLine.description == 'Analysis & Programming Services'
		assert fbLine.quantity == 2.5
		assert fbLine.unitCost == 100.00
		assert fbLine.tax1Name == 'GST'
		assert fbLine.tax1Percent == '5.00'
		def fbLine1 = fbInv.lines[1]
		assert fbLine1.name == 'A&P-1'
		assert fbLine1.description == 'Analysis & Programming Services'
		assert fbLine1.quantity == 5
		assert fbLine1.unitCost == 97.75
		assert fbLine1.tax1Name == 'GST'
		assert fbLine1.tax1Percent == '5.00'
	}	

	def simpleInvoice() {
		def endOfLastMonth = new LocalDate().withDayOfMonth(1).minusDays(1).toDate()
		def beginningOfLastMonth = new LocalDate().minusMonths(1).withDayOfMonth(1).toDate()
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
		
		return inv
	}
}
