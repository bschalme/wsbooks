package ca.airspeed.wsbooks



import grails.test.mixin.*

import org.joda.time.DateTime;
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class TsheetsServiceTests {
	def tsheetsService
	def freshbooksApiConn
	 
	void testGetToken() {
		assert tsheetsService != null
		// assert sampleService.getConfigParm() == 'Airspeed Consulting'
		// def token = sampleService.getToken()
		// assert token
		// assert token.length() > 0
		// log.info('Logged into TSheets successfully.')
		// def result = sampleService.logout(token)
		// sampleService.methodOne()
		// sampleService.methodTwo()
		// tsheetsService.checkStatus()
	}
	
	void testGetTimesheets() {
		assert tsheetsService != null
		DateTime yesterday = new DateTime().minusDays(1)
		def timesheetsJson = tsheetsService.findTimesheetsByDateBetween(yesterday.toDate(), yesterday.toDate())
		assert timesheetsJson != null
	}
	
	void testFreshbooks() {
		assert freshbooksApiConn  != null
		def inv = freshbooksApiConn.getInvoice(331330)
		println 'Invoice #' + inv.number
		println 'Organization is ' + inv.organization
	}
}
