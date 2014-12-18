package ca.airspeed.wsbooks



import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Control)
class ControlTests {

    void testConstraints() {
       def control = new Control(tsheetsLastFetchedDate:null)
	   assert !control.validate()
	   control.rowName = "Test Row"
	   control.tsheetsLastFetchedDate = new Date()
	   assert control.validate()
    }
}
