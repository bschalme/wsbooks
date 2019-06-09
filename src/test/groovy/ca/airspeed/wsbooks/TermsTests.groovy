package ca.airspeed.wsbooks;

import org.springframework.context.ConfigurableApplicationContext

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TermsTests extends Specification implements DomainUnitTest<Terms>, MultiDatasourceTest {

	@Override
	Closure doWithSpring() {
		return {
			configDatasource(application.mainContext as ConfigurableApplicationContext, "opensync")
		}
	}

	void "Test the constraints"() {
		expect:
		!domain.validate(['listID', 'fullName'])
		domain.errors["listID"].code == 'nullable'
		domain.errors["fullName"].code == 'nullable'

		when:
		domain.listID = 'ABC-123'
		domain.fullName = 'Net 37'

		then:
		domain.validate()
	}
}
