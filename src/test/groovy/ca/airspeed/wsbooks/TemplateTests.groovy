package ca.airspeed.wsbooks;

import org.springframework.context.ConfigurableApplicationContext

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class TemplateTests extends Specification implements DomainUnitTest<Template>, MultiDatasourceTest {

	@Override
	Closure doWithSpring() {
		return {
			configDatasource(application.mainContext as ConfigurableApplicationContext, "opensync")
		}
	}

	void "Test the constraints"() {
		expect:
		!domain.validate([
			'listID',
			'name',
			'isActive',
			'templateType',
			'status'
		])
		domain.errors["listID"].code == 'nullable'
		domain.errors["name"].code == 'nullable'
		domain.errors["isActive"].code == 'nullable'
		domain.errors["templateType"].code == 'nullable'
		domain.errors["status"].code == 'nullable'

		when:
		domain.listID = 'ABC-123'
		domain.name = 'Template Name'
		domain.isActive = 'maybe'
		domain.templateType = 'ASillyTemplate'
		domain.status = 'UPDATE'

		then:
		!domain.validate([
			'isActive',
			'templateType',
			'status'
		])
		domain.errors["isActive"].code == "not.inList"
		domain.errors["templateType"].code == "not.inList"
		domain.errors["status"].code == "not.inList"

		when:
		domain.isActive = 'true'
		domain.templateType = 'Invoice'
		domain.status = ''

		then:
		domain.validate()
	}
}
