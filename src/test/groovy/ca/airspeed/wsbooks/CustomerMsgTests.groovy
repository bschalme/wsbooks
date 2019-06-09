package ca.airspeed.wsbooks

import grails.testing.gorm.DomainUnitTest
import org.springframework.context.ConfigurableApplicationContext
import spock.lang.Specification

class CustomerMsgTests extends Specification implements DomainUnitTest<CustomerMsg>, MultiDatasourceTest {

	@Override
	Closure doWithSpring() {
		return {
			configDatasource(application.mainContext as ConfigurableApplicationContext, "opensync")
		}
	}

	void "Test the constraints"() {
		expect:
		!domain.validate(['listID', 'name', 'isActive'])
		domain.errors['listID'].code == 'nullable'
		domain.errors['name'].code == 'nullable'
		domain.errors['isActive'].code == 'nullable'

		when:
		domain.listID = 'ABC-123'
		domain.name = 'Test Message'
		domain.isActive = 'maybe'
		domain.status = 'ADD'

		then:
		!domain.validate(['isActive', 'status'])
		domain.errors['isActive'].code == 'not.inList'
		domain.errors['status'].code == 'not.inList'

		when:
		domain.isActive = 'true'
		domain.status = null

		then:
		domain.validate()
	}
}
