package ca.airspeed.wsbooks

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

	void "get timesheets from yesterday"() {
		Date yesterday = new DateTime().minusDays(1).toDate()
		def jsonSlurper = new JsonSlurper()
		def result = jsonSlurper.parseText(tsheetsRestService.findTimesheetsByDateBetween(yesterday, yesterday))
		
		expect:
		result != null
		result.results.timesheets != null
	}
}