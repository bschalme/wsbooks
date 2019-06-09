package ca.airspeed.wsbooks

import org.springframework.context.ConfigurableApplicationContext

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TimeTrackingTests extends Specification implements DomainUnitTest<TimeTracking>, MultiDatasourceTest {

	@Override
	Closure doWithSpring() {
		return {
			configDatasource(application.mainContext as ConfigurableApplicationContext, "opensync")
		}
	}

	void "Test the constraints"() {
		when:
		domain.status = 'BLAH'

		then:
		!domain.validate([
			'txnID',
			'txnDate',
			'entityRefListID',
			'customer',
			'itemService',
			'duration',
			'status'
		])
		domain.errors["txnID"].code == 'nullable'
		domain.errors["txnDate"].code == 'nullable'
		domain.errors["entityRefListID"].code == 'nullable'
		domain.errors["customer"].code == 'nullable'
		domain.errors["itemService"]?.code == 'nullable'
		domain.errors["duration"].code == 'nullable'
		domain.errors["status"].code == 'not.inList'

		when:
		domain.status = 'ADD'
		domain.txnID = 'ABC-123'
		domain.txnDate = new Date()
		domain.entityRefListID = 'EMP-1'
		domain.customer = new Customer(name: 'CUST-1')
		domain.itemService = new ItemService(name: 'ITEM-1')
		domain.duration = 'X9XHR8'

		then:
		!domain.validate(['duration'])
		domain.errors["duration"]?.code == 'matches.invalid'

		when:
		domain.duration = 'PT0H30M'
		domain.status = ''

		then:
		domain.validate()
	}

	void testGetDurationInMinutes() {
		def ts = new TimeTracking()

		ts.duration = 'PT0H30M'
		assert ts.durationInMinutes == 30

		ts.duration = 'PT1H0M'
		assert ts.durationInMinutes == 60

		ts.duration ='PT1H30M'
		assert ts.durationInMinutes == 90
	}

	void testSetDurationInMinutes() {
		def ts = new TimeTracking()

		ts.durationInMinutes = 120
		assert ts.duration == 'PT2H0M'

		ts.durationInMinutes = 90
		assert ts.duration == 'PT1H30M'

		ts.durationInMinutes = 30
		assert ts.duration == 'PT0H30M'
	}
}
