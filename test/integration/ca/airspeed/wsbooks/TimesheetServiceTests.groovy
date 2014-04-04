package ca.airspeed.wsbooks



import java.awt.GraphicsConfiguration.DefaultBufferCapabilities;

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TimesheetService)
class TimesheetServiceTests {
	def timesheetService

	@Test
    void testFetchFromTsheets() {
        timesheetService.fetchTimesheetsFromTsheets()
    }
}
