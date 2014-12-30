package ca.airspeed.wsbooks

import grails.test.mixin.*

import org.joda.time.DateMidnight;
import org.joda.time.DateTime;
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TimesheetService)
class TimesheetServiceTests {
	def timesheetService

	@BeforeClass
	static void setUpBeforeClass() {
	    TsheetsUserXref userXref = new TsheetsUserXref()
		userXref.userName = 'Brian Schalme'
		userXref.tsheetsUserId = 186512
		userXref.qbEntityListId = '20000-929923144'
		userXref.save(flush: true, validateOnSave: true)
		
		TsheetsJobcodeXref jobXref = new TsheetsJobcodeXref()
		jobXref.jobName = 'Test Customer and Job'
		jobXref.tsheetsJobcodeId = 7842272
		jobXref.qbCustomerListId = '7F0000-1069293389'
		jobXref.qbItemServiceListId = '140000-1069940598'
		jobXref.save(flush: true, failOnError: true)
	}

	@Test
    void testFetchFromTsheets() {
		Control ctl = Control.findByRowName("Control Record")
		ctl.tsheetsLastFetchedDate = new DateMidnight().minusDays(2).toDate()
		ctl.save(failOnError: true, flush: true)
        timesheetService.fetchTimesheetsFromTsheets()
    }
}
