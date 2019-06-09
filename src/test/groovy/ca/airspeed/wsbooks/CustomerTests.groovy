package ca.airspeed.wsbooks

import org.springframework.context.ConfigurableApplicationContext

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class CustomerTests extends Specification implements DomainUnitTest<Customer>, MultiDatasourceTest {

	@Override
	Closure doWithSpring() {
		return {
			configDatasource(application.mainContext as ConfigurableApplicationContext, "opensync")
		}
	}

	void "Test the constraints"() {
		expect:
		!domain.validate(['listID', 'name'])
		domain.errors['listID'].code == 'nullable'
		domain.errors['name'].code == 'nullable'

		when:
		domain.listID = 'ABC-456'

		then:
		!domain.validate(['name'])
		domain.errors['name'].code == 'nullable'

		when:
		domain.status = 'XXX'

		then:
		!domain.validate(['status'])
		domain.errors['status'].code == 'not.inList'

		when:
		domain.status = null

		then:
		!domain.validate(['status'])
		domain.errors['status'].code == 'nullable'

		when: 'Valid Customer'
		domain.name = 'MegaCorp'
		domain.status = 'UPDATE'

		then:
		domain.validate()
	}
}
