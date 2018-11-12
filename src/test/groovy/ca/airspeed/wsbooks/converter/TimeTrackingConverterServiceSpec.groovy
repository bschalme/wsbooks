package ca.airspeed.wsbooks.converter

import org.codehaus.groovy.grails.io.support.ClassPathResource;
import org.codehaus.groovy.grails.io.support.Resource;
import java.time.LocalDate;

import ca.airspeed.wsbooks.Customer;
import ca.airspeed.wsbooks.ItemService;
import ca.airspeed.wsbooks.TsheetsJobcodeXref;
import ca.airspeed.wsbooks.TsheetsUserXref;

import grails.test.mixin.TestFor
import groovy.json.JsonSlurper;
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(TimeTrackingConverterService)
class TimeTrackingConverterServiceSpec extends Specification {
	
	def setup() {
		mockDomain(TsheetsUserXref, [
			[userName: "Jack Sparrow", tsheetsUserId: 186512, qbEntityListId: "USR-123"]
			])
		mockDomain(TsheetsJobcodeXref, [
			[jobName: 'The Job', tsheetsJobcodeId: 7842272, qbCustomerListId: 'CUST-123', qbItemServiceListId: 'SVC-456']
			])
		mockDomain(Customer, [
			[listID: 'CUST-123', name: 'The CUST-123 Company', fullName: 'The CUST-123 Company', status: 'ADD']
			])
		mockDomain(ItemService, [
			[listID: 'SVC-456', name: 'App Dev Services', fullName: 'App Dev Services', isActive: true, isTaxIncluded: false, status: 'ADD']
			])
	}

    def cleanup() {
    }

    void "happy path test"() {
		JsonSlurper slurper =  new JsonSlurper()
		Resource resource = new ClassPathResource("resources/tsheetsTimesheets.json")
		def json = slurper.parseText(resource.file.text)
		def qbTimeTrackings = service.convertFromTsheetsTimesheets(json)
		
		expect:
		qbTimeTrackings != null
		qbTimeTrackings.size() == 4
		def entry = qbTimeTrackings.find{ it.txnID == '179687502'}
		entry.txnID == '179687502'
		entry.txnDate == new LocalDate("2014-12-22").toDate()
		entry.entityRefListID == "USR-123"
		entry.duration == "PT4H0M"
		entry.customer?.listID == "CUST-123"
		entry.itemService?.listID == "SVC-456"
		entry.notes == "0800-1200:LG007205:THE Monitoring Enhancements - Reqmts, Spec, Development:Tweak JavaScript so that a disabled text box shows the Reason description instead of the disabled drop down list."
		entry.billableStatus == "Billable"
		entry.status == "ADD"
		def entry2 = qbTimeTrackings.find{ it.txnID == '179755168'}
		entry2.duration == "PT0H30M"
    }
}
