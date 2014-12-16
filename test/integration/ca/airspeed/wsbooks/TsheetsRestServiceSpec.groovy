package ca.airspeed.wsbooks

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
}
