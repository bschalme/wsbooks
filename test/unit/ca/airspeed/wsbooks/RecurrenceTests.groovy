package ca.airspeed.wsbooks



import grails.test.mixin.*
import org.junit.*

import ca.airspeed.wsbooks.Recurrence;

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Recurrence)
class RecurrenceTests {

    void testSomething() {
       def recurrence = new Recurrence(entityNumber: '347')
	   assertTrue recurrence.validate()
    }
}
