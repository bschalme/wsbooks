package ca.airspeed.wsbooks



import grails.test.mixin.*
import groovy.json.JsonSlurper;

import org.joda.time.DateTime;
import org.junit.*
import org.springframework.http.HttpStatus;

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
class SampleServiceTests {
	
	def sampleService

	@Test
    void testSomething() {
        sampleService.doSomeWork()
    }
	
}
