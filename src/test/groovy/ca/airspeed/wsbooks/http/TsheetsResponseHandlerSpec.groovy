package ca.airspeed.wsbooks.http

import ca.airspeed.wsbooks.exception.TsheetsException;

import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.UNAUTHORIZED

import grails.plugins.rest.client.RestResponse
import grails.test.mixin.TestFor;
import grails.test.mixin.TestMixin;
import grails.test.mixin.support.GrailsUnitTestMixin;
import groovy.json.JsonBuilder;
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException
import spock.lang.Specification

class TsheetsResponseHandlerSpec extends Specification {
	
	def tsheetsResponseHandler
	
	def setup() {
		tsheetsResponseHandler = new TsheetsResponseHandler()
	}

	def cleanup() {
	}

	void "test 200 OK"() {
		given:
		def builder = new JsonBuilder()
		def root = builder.call([message : "Hello World!"])
		def responseEntity = new ResponseEntity(builder.toPrettyString(), OK)
		def myResponse = new RestResponse(responseEntity)

		when:
		def results = tsheetsResponseHandler.handle(myResponse)

		then:
		results.message == "Hello World!"
	}

	void "test 401 Unauthorized"() {
		given:
		def builder = new JsonBuilder()
		def root = builder.call([error : "invalid_grant", error_description : "The access token provided has expired"])
		def responseEntity = new ResponseEntity(builder.toPrettyString(), UNAUTHORIZED)
		def myResponse = new RestResponse(responseEntity)
		
		when:
		def results = tsheetsResponseHandler.handle(myResponse)

		then:
		thrown(TsheetsException)
	}
}
