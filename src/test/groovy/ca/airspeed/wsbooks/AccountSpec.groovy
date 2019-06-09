package ca.airspeed.wsbooks

import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.support.ConfigurableConversionService

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class AccountSpec extends Specification implements DomainUnitTest<Account>, MultiDatasourceTest {

	@Override
	Closure doWithSpring() {
		return {
			configDatasource(application.mainContext as ConfigurableApplicationContext, "opensync")
		}
	}

	def setup() {
	}

	def cleanup() {
	}

	void "Test the constraints"() {
		expect:
		!domain.validate([
			'listID',
			'name',
			'fullName',
			'isActive',
			'accountType',
			'status'
		])
		domain.errors['listID'].code == 'nullable'
		domain.errors['name'].code == 'nullable'
		domain.errors['fullName'].code == 'nullable'
		domain.errors['isActive'].code == 'nullable'
		domain.errors['accountType'].code == 'nullable'
		domain.errors['status'].code == 'nullable'
		
		when:
		domain.listID = 'ABC-123'
		domain.name = 'TestAccount'
		domain.fullName = 'TestAccount Full Name'
		domain.isActive = 'maybe'
		domain.accountType = 'SillyAccount'
		domain.status = 'UPDATE'

		then:
		!domain.validate([
			'isActive',
			'accountType',
			'status'
		])
		domain.errors['isActive'].code == 'not.inList'
		domain.errors['accountType'].code == 'not.inList'
		domain.errors['status'].code == 'not.inList'
		
		when:
		domain.isActive = 'true'
		domain.accountType = 'Income'
		domain.status = 'ADD'
		
		then:
		domain.validate()
	}
}
