
package ca.airspeed.wsbooks

import org.springframework.core.convert.converter.Converter
import org.springframework.core.convert.support.ConfigurableConversionService

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class VendorTests extends Specification implements DomainUnitTest<Vendor> {

	static{
		System.setProperty("grails.gorm.connections", "opensync")
	}

	@Override
	Closure doWithSpring() {
		return {
			ConfigurableConversionService conversionService = application.mainContext.getEnvironment().getConversionService()
			conversionService.addConverter(new Converter<String, Map>() {
						@Override
						Map convert(String source) {
							source.split(",").collectEntries({
								[(it):it]
							})
						}
					})
		}
	}

	void 'listID cannot be null'() {
		when:
		def domain = new Vendor()
		domain.listID = null

		then:
		!domain.validate(['listID'])
		domain.errors['listID'].code == 'nullable'
	}

	void 'name cannot be null'() {
		when:
		def domain = new Vendor()

		then:
		!domain.validate(['name'])
		domain.errors['name'].code == 'nullable'
	}

	void 'status cannot be null'() {
		when:
		def domain = new Vendor()

		then:
		!domain.validate(['status'])
		domain.errors['status'].code == 'nullable'
	}

	void 'isActive cannot be null'() {
		when:
		def domain = new Vendor()

		then:
		!domain.validate(['isActive'])
		domain.errors['isActive'].code == 'nullable'
	}

	void 'Invalid status'() {
		when:
		def domain = new Vendor()
		domain.status = 'XXX'

		then:
		!domain.validate(['status'])
		domain.errors['status'].code == 'not.inList'
	}

	void 'Happy Path'() {
		when:
		def domain = new Vendor()
		domain.listID = 'ABC-123'
		domain.name = 'MegaCorp'
		domain.isActive = 'true'
		domain.status = 'UPDATE'

		then:
		domain.validate()
	}

	void testConstraints() {
		def vendor = new Vendor()
		assert !vendor.validate()
		assert "nullable" == vendor.errors["listID"].code
		assert "nullable" == vendor.errors["name"].code

		vendor.listID = 'ABC-456'
		assert !vendor.validate()
		assert "nullable" == vendor.errors["name"].code

		vendor.status = 'XXX'
		assert !vendor.validate()
		assert "not.inList" == vendor.errors["status"].code

		vendor.status = null
		assert !vendor.validate()

		vendor.name = 'MegaCorp'
		vendor.status = 'UPDATE'
		vendor.isActive = 'true'
		assert vendor.validate()
	}
}
