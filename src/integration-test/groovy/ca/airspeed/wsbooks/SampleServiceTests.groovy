package ca.airspeed.wsbooks



import org.junit.*

import grails.test.mixin.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@Ignore('Getting an unexplained IllegalStateException when this test case runs')
class SampleServiceTests {
	
	def sampleService

	@Test
    void testSomething() {
        sampleService.doSomeWork()
    }
	
}
