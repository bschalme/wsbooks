package ca.airspeed.wsbooks

import org.joda.time.DateMidnight
import org.joda.time.DateTime;

import grails.test.spock.IntegrationSpec;
import groovy.json.JsonBuilder;
import groovy.json.JsonSlurper;
import spock.lang.Specification


class TsheetsRestServiceSpec extends Specification {

	TsheetsRestService tsheetsRestService
	
    def setup() {
    }

    def cleanup() {
    }

    void "get last modified timestamps"() {
		String result = tsheetsRestService.lastModifiedTimestamps
		
		expect:
		result != null
		def slurper = new JsonSlurper()
		def json = slurper.parseText(result)
		json.results != null
		json.results.last_modified_timestamps != null
		json.results.last_modified_timestamps.current_user != ""
    }

	void "get timesheets"() {
		Date theDay = new DateMidnight(2014, 12, 22).toDate()
		def result = tsheetsRestService.findTimesheetsByDateBetween(theDay, theDay)
		
		expect:
		result != null
		result.results.timesheets?.size() > 2
		def timesheet = result.results.timesheets."179687502"
		assert timesheet.user_id == 186512
		assert timesheet.jobcode_id == 7842272
	}
}