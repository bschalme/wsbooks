package ca.airspeed.wsbooks

import grails.test.mixin.*

import org.joda.time.DateTime;
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TimesheetService)
class TimesheetServiceTests {
	def timesheetService

	@Test
    void testFetchFromTsheets() {
		Control ctl = Control.findByRowName("Control Record")
		ctl.tsheetsLastFetchedDate = new DateTime().minusDays(2).toDate()
		ctl.save(failOnError: true, flush: true)
        timesheetService.fetchTimesheetsFromTsheets()
    }
}
