package ca.airspeed.wsbooks

import org.springframework.context.ConfigurableApplicationContext

import grails.testing.gorm.DomainUnitTest
import spock.lang.Specification

class InvoiceLineDetailTests extends Specification implements DomainUnitTest<InvoiceLineDetail>, MultiDatasourceTest {

	@Override
	Closure doWithSpring() {
		return {
			configDatasource(application.mainContext as ConfigurableApplicationContext, "opensync")
		}
	}

	void "Test the constraints"() {
		expect:
		!domain.validate(['txnLineID', 'item', 'quantity'])
		domain.errors["txnLineID"].code == 'nullable'
		domain.errors["item"].code == 'nullable'
		// assert "nullable" == domain.errors["description"]?.code
		domain.errors["quantity"].code == 'nullable'
		// assert "nullable" == domain.errors["rate"].code
		// assert "missing.sales.tax.code" == domain.errors["salesTaxCodeRefListID"].code

		when:
		domain.txnLineID = "DEF-456"
		domain.item = new Items(fullName: 'A&P:$100/hr', listID: '140000-1069940598')
		domain.description = ''
		domain.quantity = '23A'
		domain.salesTaxCodeRefFullName = 'G'

		then:
		!domain.validate(['quantity'])
		domain.errors["quantity"].code == 'notANumber'

		when:
		domain.quantity = '-0.1'

		then:
		!domain.validate(['quantity'])
		domain.errors["quantity"].code == 'min.notmet'

		when:
		domain.quantity= '125.5'
		domain.rate = '100'
		domain.description = 'Analysis & Programming Services'

		then:
		!domain.validate(['invoice'])
		domain.errors["invoice"].code == 'nullable'

		when:
		domain.invoice = new Invoice()

		then:
		domain.validate()
	}
}
