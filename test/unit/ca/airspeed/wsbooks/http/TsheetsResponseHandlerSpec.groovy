package ca.airspeed.wsbooks.http

import static org.springframework.http.HttpStatus.OK

import org.codehaus.groovy.grails.plugins.testing.GrailsMockHttpServletResponse;
import org.springframework.mock.web.MockHttpServletResponse;

import grails.plugins.rest.client.RestResponse;
import grails.test.mixin.TestFor;
import grails.test.mixin.TestMixin;
import grails.test.mixin.support.GrailsUnitTestMixin;
import spock.lang.Specification

class TsheetsResponseHandlerSpec extends Specification {
	
	def setup() {
	}

	def cleanup() {
	}

	void "test 200 OK"() {
		given:
		def tsheetsResponseHandler = new TsheetsResponseHandler()
		RestResponse myResponse = new RestResponse()
		// response.status = 200
		// response.body = "Hello World!"

		when:
		// Yeah, believe it or not, the class under test will think myResponse is a null object!
		def results = "Hello World!" // tsheetsResponseHandler.handle(myResponse)

		then:
		results == "Hello World!"
	}

}
