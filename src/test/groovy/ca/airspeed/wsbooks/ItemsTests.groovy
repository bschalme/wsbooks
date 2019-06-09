package ca.airspeed.wsbooks;

import org.springframework.context.ConfigurableApplicationContext

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class ItemsTests extends Specification implements DomainUnitTest<Items>, MultiDatasourceTest {

	@Override
	Closure doWithSpring() {
		return {
			configDatasource(application.mainContext as ConfigurableApplicationContext, "opensync")
		}
	}

	void "Test the constraints"() {
		expect:
		assert !domain.validate([
			'listID',
			'fullName',
			'tableName'
		])
		domain.errors["listID"].code == 'nullable'
		domain.errors["fullName"].code == 'nullable'
		domain.errors["tableName"].code == 'nullable'

		when:
		domain.listID = 'ABC-123'
		domain.fullName = 'Alphabet Soup'
		domain.tableName = 'customer'

		then:
		!domain.validate(['tableName'])
		domain.errors["tableName"].code == 'not.inList'

		when:
		domain.tableName = 'itemservice'

		then:
		domain.validate()
	}
}
