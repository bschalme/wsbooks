
package ca.airspeed.wsbooks

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertNotNull
import static org.junit.Assert.assertTrue
import static org.junit.Assert.fail

import grails.test.mixin.*

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.junit.*

import java.text.SimpleDateFormat

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
// @TestFor(InvoiceService)
// @Mock([Invoice, InvoiceLineDetail])
class InvoiceServiceTests {
	
	def service = new InvoiceService()
	
	@Before
	void setUp() {
		Invoice.list().each {
			it.delete()
		}
		InvoiceLineDetail.list().each {
			it.delete()
		}
	}

	@Test
	void testCreateRecurringInvoice() {
		Account arAccount = Account.findByAccountType('AccountsReceivable')
		def inv347 = new Invoice(refNumber: '347', arAccount: arAccount)
		inv347.txnID = UUID.randomUUID().toString()
		inv347.id = inv347.txnID
		inv347.txnDate = new LocalDate().minusMonths(5).withDayOfMonth(1).toDate()
		inv347.customer = Customer.get('800000EB-1251942204')
		inv347.terms = StandardTerms.get('20000-929918818')
		inv347.subtotal = 60
		inv347.salesTaxPercentage = 5.00
		inv347.salesTaxTotal = 3
		inv347.appliedAmount = -63
		inv347.balanceRemaining = 0
		inv347.isPaid = true
		inv347.isToBePrinted = false
		inv347.status = 'ADD'
		
		def dtl347 = new InvoiceLineDetail()
		dtl347.txnLineID = UUID.randomUUID().toString()
		dtl347.id = dtl347.txnLineID
		dtl347.item = Items.get('140000-1069940598')
		dtl347.description = 'Analysis & Programming Services (hours)'
		dtl347.quantity = 12.5
		dtl347.rate = 100.00
		dtl347.salesTaxCodeRefFullName = 'G'
		inv347.addToDetailLines(dtl347)
		inv347.save(failOnError: true, flush: true)
		
		def r = new Recurrence(nickname:'Test Invoice Recurrence', entityName:'Invoice')
		r.entityNumber = '347'
		r.frequency = 'Monthly'
		r.txnTiming = 'In Advance'
		r.nextRunDate = new LocalDate().withDayOfMonth(1).toDate()
		r.finalRunDate = new LocalDate().plusMonths(12).withDayOfMonth(1).toDate()
		r.active = true

		service.createRecurringInvoice('348', r)

		def resultInv = Invoice.findByRefNumber('348')
		assertNotNull 'Expected Invoice 348 to be created', resultInv
		assertNotNull('New Invoice has a null TxnID.', resultInv.txnID)
		assertNotNull('New Invoice has a null id.', resultInv.id)
		assertFalse('New Invoice should not have the same TxnID as the Recurrence Invoice.',
				resultInv.txnID.equals(inv347.txnID))
		def expectedTxnDate = new LocalDate().withDayOfMonth(1).toDate()
		assert expectedTxnDate == resultInv.txnDate
		assert resultInv.customer.listID == '800000EB-1251942204'
		assert resultInv.appliedAmount == 0
		assert resultInv.balanceRemaining == 63
		assert resultInv.isPaid == 'false'
		assert resultInv.isToBePrinted == 'true'
		assert resultInv.terms.name == 'Net 30'
		def fromFmt = new SimpleDateFormat("MMM d")
		def toFmt = new SimpleDateFormat("dd, yyyy")
		def expectedOther = fromFmt.format(expectedTxnDate) + ' - ' + toFmt.format(new DateTime(expectedTxnDate).
			plusMonths(1).withDayOfMonth(1).minusDays(1).toDate())
		assert resultInv.other == expectedOther
		assert resultInv.status == 'ADD'
		
		def resultDtls = resultInv.detailLines
		assert resultDtls.size() == 1
		resultDtls.each { resultDtl ->
			assert resultDtl.item.fullName == 'A&P:$100.00/hr'
			assert resultDtl.description == 'Analysis & Programming Services (hours)'
			assert resultDtl.quantity == '12.5'
			assert resultDtl.rate == '100.00'
			assert resultDtl.salesTaxCodeRefFullName == 'G'
		}
	}

	@Test
	void testGetNextRefNumber() {
		def arAccount = Account.findByFullName('Accounts Receivable')
		def invLine = new InvoiceLineDetail(itemRefListID: 'ABC-123', quantity: 1)
		invLine.item = Items.get('140000-1069940598')
		def terms = StandardTerms.get('20000-929918818')
		invLine.txnLineID = UUID.randomUUID().toString()
		def inv205 = new Invoice(refNumber: '205')
		inv205.txnID = UUID.randomUUID().toString()
		inv205.id = inv205.txnID
		inv205.txnDate = new LocalDate().withDayOfMonth(1).toDate()
		inv205.arAccount = arAccount
		inv205.customer = Customer.get('800000EB-1251942204')
		inv205.terms = terms
		inv205.status = 'ADD'
		inv205.addToDetailLines(invLine)
		inv205.save(failOnError: true, flush: true)

		def inv427 = new Invoice(refNumber: '427')
		inv427.txnID = UUID.randomUUID().toString()
		inv427.id = inv427.txnID
		inv427.txnDate = new LocalDate().withDayOfMonth(1).toDate()
		inv427.arAccount = arAccount
		inv427.customer = Customer.get('CB0000-1190817876')
		inv427.terms = terms
		inv427.status = 'ADD'
		inv427.addToDetailLines(invLine)
		inv427.save(failOnError: true)

		def inv99 = new Invoice(refNumber: '99')
		inv99.txnID = UUID.randomUUID().toString()
		inv99.id = inv99.txnID
		inv99.txnDate = new LocalDate().withDayOfMonth(1).toDate()
		inv99.arAccount = arAccount
		inv99.customer = Customer.get('CB0000-1190817876')
		inv99.terms = terms
		inv99.status = 'ADD'
		inv99.addToDetailLines(invLine)
		inv99.save(failOnError: true, flush: true)

		def invoices = Invoice.list()
		assertEquals("Number of invoices;", 3, invoices.size())

		def next = service.getNextRefNumber()
		assert next == '428'
	}
	
	@Test
	void testCreateTimeInvoice() {
		// Given:
		def t1 = new TimeTracking()
		t1.txnID = 'TIME-1'
		t1.txnDate = new Date()
		t1.entityRefListID = '20000-929923144'
		t1.customer = Customer.get('CD0000-1190818043')
		t1.itemService = ItemService.get('140000-1069940598')
		t1.durationInMinutes = 120
		t1.billableStatus = 'Billable'
		t1.status = ''
		t1.save(failOnError: true)
		
		def t2 = new TimeTracking()
		t2.txnID = 'TIME-2'
		t2.txnDate = new Date()
		t2.entityRefListID = '20000-929923144'
		t2.customer = Customer.get('CD0000-1190818043')
		t2.itemService = ItemService.get('140000-1069940598')
		t2.durationInMinutes = 30
		t2.billableStatus = 'Billable'
		t2.status = ''
		t2.save(failOnError: true)
		
		def t3 = new TimeTracking()
		t3.txnID = 'TIME-3'
		t3.txnDate = new Date()
		t3.entityRefListID = '20000-929923144'
		t3.customer = Customer.get('CD0000-1190818043')
		t3.itemService = ItemService.get('A0000-1011121334')
		t3.durationInMinutes = 60
		t3.billableStatus = 'Billable'
		t3.status = ''
		t3.save(failOnError: true)
		
		def t4 = new TimeTracking()
		t4.txnID = 'TIME-4'
		t4.txnDate = new Date()
		t4.entityRefListID = '20000-929923144'
		t4.customer = Customer.get('CD0000-1190818043')
		t4.itemService = ItemService.get('A0000-1011121334')
		t4.durationInMinutes = 45
		t4.billableStatus = 'Billable'
		t4.status = 'ADD'
		t4.save(failOnError: true)
		
		def customer = Customer.get('CD0000-1190818043')
		assert customer.fullName == 'FNT:Website Hosting'
		assert customer.salesTaxCodeRefListID == '80000003-0'
		def endOfLastMonth = new DateTime().withDayOfMonth(1).minusDays(1).toDate()
		def beginningOfLastMonth = new DateTime().minusMonths(1).withDayOfMonth(1).toDate()
		def startOfMonthFm = new SimpleDateFormat('MMM d')
		def endOfMonthFm = new SimpleDateFormat('d, yyyy')

		// When
		def invCreated = service.createTimeByCustomerAndDate(customer, endOfLastMonth)
		
		// Then
		assert invCreated != null
		assert invCreated.invoiceNumber == '1'
		assert invCreated.sourceSystem == 'QuickBooks'
		assert invCreated.clientID == 'CB0000-1190817876'
		assert invCreated.date == endOfLastMonth
		assert invCreated.terms == 'Net 30'
		def expectedOther = startOfMonthFm.format(beginningOfLastMonth) + ' - '  + endOfMonthFm.format(endOfLastMonth)
		assert invCreated.period == expectedOther
		assert invCreated.notes == 'Airspeed Consulting is a division of 4020774 Manitoba Ltd.'
		assert invCreated.lines.size() == 2
		def lineCreated = invCreated.lines[0]
		assert lineCreated.name == 'A&P:$100.00/hr'
		assert lineCreated.description == 'Analysis & Programming Services'
		assert lineCreated.quantity == 2.5
		assert lineCreated.unitCost == 100.00
		assert lineCreated.taxes?.get(0)?.name == 'GST'
		assert lineCreated.taxes?.get(0)?.percent == 5.00
		lineCreated = invCreated.lines[1]
		assert lineCreated.quantity == 1.75

		Invoice inv = Invoice.findByRefNumber(invCreated.invoiceNumber)
		assert inv != null
		assert StringUtils.isNotBlank(inv.txnID)
		assert inv.refNumber == '1'
		assert inv.txnDate == endOfLastMonth
		assert inv.arAccount.accountType == 'AccountsReceivable'
		assert inv.template.listID == 'C0000-1078107584'
		assert inv.customer.listID == 'CD0000-1190818043'
		assert inv.customer.fullName == 'FNT:Website Hosting'
		assert inv.terms.listID == '20000-929918818'
		assert inv.terms.name == 'Net 30'
		assert inv.customerMsg.name?.startsWith('Questions concerning this invoice?')
		assert inv.isToBePrinted == 'false'
		assert inv.other == expectedOther
		assert inv.status == 'ADD'
		
		def invLines = inv.detailLines
		assert invLines.size() == 2
		InvoiceLineDetail line = invLines.find() {
			it.item.listID == '140000-1069940598'
		}
		assert StringUtils.isNotBlank(line.txnLineID)
		assert line.quantity == '2.5'
		
		line = invLines.find() {
			it.item.listID == 'A0000-1011121334'
		}
		assert StringUtils.isNotBlank(line.txnLineID)
		assert line.quantity == '1.75'
		
		t1 = TimeTracking.get('TIME-1')
		assert t1.billableStatus == 'HasBeenBilled'
		assert t1.status == 'UPDATE'
		t2.read('TIME-2')
		assert t2.billableStatus == 'HasBeenBilled'
		assert t2.status == 'UPDATE'
		t3.read('TIME-3')
		assert t3.billableStatus == 'HasBeenBilled'
		assert t3.status == 'UPDATE'
		t4.read('TIME-4')
		assert t4.billableStatus == 'HasBeenBilled'
		assert t4.status == 'ADD'
	}

	@Test
	void testItemIsGstAndCustomerIsHst() {
		// Given:
		def t5 = new TimeTracking()
		t5.txnID = 'TIME-1'
		t5.txnDate = new Date()
		t5.entityRefListID = '20000-929923144'
		t5.customer = Customer.get('80000156-1554056987')
		t5.itemService = ItemService.get('140000-1069940598')
		t5.durationInMinutes = 120
		t5.billableStatus = 'Billable'
		t5.status = ''
		t5.save(failOnError: true)
		
		def customer = Customer.get('80000156-1554056987')
		assert customer.fullName == 'HCMWorks:Investors Group'
		assert customer.salesTaxCodeRefListID == '80000007-1558482994'
		def endOfLastMonth = new DateTime().withDayOfMonth(1).minusDays(1).toDate()
		def beginningOfLastMonth = new DateTime().minusMonths(1).withDayOfMonth(1).toDate()
		def startOfMonthFm = new SimpleDateFormat('MMM d')
		def endOfMonthFm = new SimpleDateFormat('d, yyyy')

		// When
		def invCreated = service.createTimeByCustomerAndDate(customer, endOfLastMonth)
		
		// Then
		assert invCreated != null
		assert invCreated.invoiceNumber == '1'
		assert invCreated.sourceSystem == 'QuickBooks'
		assert invCreated.clientID == '80000155-1554056926'
		assert invCreated.date == endOfLastMonth
		assert invCreated.terms == 'Net 30'
		def expectedOther = startOfMonthFm.format(beginningOfLastMonth) + ' - '  + endOfMonthFm.format(endOfLastMonth)
		assert invCreated.period == expectedOther
		assert invCreated.notes == 'Airspeed Consulting is a division of 4020774 Manitoba Ltd.'
		// This set of test data has some unbilled TimeTracking entries, so this is why there is one entry in addition
		// to the one we expect:
		assert invCreated.lines.size() == 2
		def lineCreated = invCreated.lines[1]
		assert lineCreated.name == 'A&P:$100.00/hr'
		assert lineCreated.description == 'Analysis & Programming Services'
		assert lineCreated.quantity == 2
		assert lineCreated.unitCost == 100.00
		assert lineCreated.taxes?.get(0)?.name == 'HST'
		assert lineCreated.taxes?.get(0)?.percent == 13.00
	}

	@Test
	void testNoTimeInvoice() {
		// Given
		def endOfLastMonth = new DateTime().withDayOfMonth(1).minusDays(1).toDate()
		def cust = Customer.get('5A0000-1013976766')
		
		// When
		service.createTimeByCustomerAndDate(cust, endOfLastMonth)
		
		// Then
		def invoices = Invoice.findAllByCustomerAndTxnDate(Customer.get('5A0000-1013976766'), endOfLastMonth)
		assert invoices.size() == 0
	}

}
