package ca.airspeed.wsbooks



import java.util.Date;

import grails.test.mixin.*

import org.joda.time.LocalDate;
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class RecurrenceServiceTests {
	
	def service = new RecurrenceService()
	
	@Before
	void setUp() {
		Recurrence.list().each {
			it.delete()
		}
	}

	void testIncrementMonthly() {
		def r = new Recurrence(nickname:"Web hosting for MegaCorp",
		entityName:"Invoice",
		entityNumber:'351',
		frequency:"Monthly",
		txnTiming:"In Advance",
		nextRunDate:new LocalDate().plusMonths(1).withDayOfMonth(1).toDate(),
		finalRunDate:new LocalDate().plusMonths(12).withDayOfMonth(1).toDate(),
		active:true)
		
		service.increment(r)
		
		r = Recurrence.findByEntityNumber('351')
		assert r != null
		assert r.nickname == 'Web hosting for MegaCorp'
		assert r.nextRunDate == new LocalDate().plusMonths(2).withDayOfMonth(1).toDate()
	}
	
	void testIncrementFinalRecurrence() {
		def r = new Recurrence(nickname:"Web hosting for MegaCorp",
			entityName:"Invoice",
			entityNumber:'351',
			frequency:"Monthly",
			txnTiming:"In Advance",
			nextRunDate:new LocalDate().plusMonths(1).withDayOfMonth(1).toDate(),
			finalRunDate:new LocalDate().plusMonths(1).withDayOfMonth(1).toDate(),
			active:true)
			
			service.increment(r)
			
			r = Recurrence.findByEntityNumber('351')
			assert r != null
			assert r.nickname == 'Web hosting for MegaCorp'
			assert r.nextRunDate == null
	
	}
}
