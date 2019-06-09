package ca.airspeed.wsbooks

import grails.test.mixin.TestFor
import grails.testing.gorm.DomainUnitTest
import org.springframework.context.ConfigurableApplicationContext
import spock.lang.Specification

class InvoiceTests extends Specification implements DomainUnitTest<Invoice>, MultiDatasourceTest {

	@Override
	Closure doWithSpring() {
		return {
			configDatasource(application.mainContext as ConfigurableApplicationContext, "opensync")
		}
	}

	void "Test the constraints"() {
		expect:
		!domain.validate(['txnID', 'customer', 'txnDate', 'refNumber', 'arAccount', 'terms'])
		domain.errors["txnID"].code == 'nullable'
		domain.errors["customer"].code == 'nullable'
		domain.errors["txnDate"].code == 'nullable'
		domain.errors["refNumber"].code == 'nullable'
		domain.errors["arAccount"].code == 'nullable'
		domain.errors["terms"].code == 'nullable'

		when:
		domain.txnID = 'ABC-123'
		domain.arAccount = new Account()
		domain.customer = new Customer(name: "MegaCorp:Real Estate Web Service")
		domain.txnDate = new Date()
		domain.refNumber = '99999'
		domain.customerMsg = new CustomerMsg()
		domain.terms = new StandardTerms(listID: '20000-929918818', name: 'Net 30')
		domain.status = 'ZZZZ'

		then:
		!domain.validate(['status', 'arAccount'])
		domain.errors["status"].code == 'not.inList'
		domain.errors["arAccount"].code == 'account.must.be.ar'

		when:
		domain.terms.name == 'Net 30'

		then:
		!domain.validate(['detailLines'])
		domain.errors["detailLines"].code == 'nullable'

		when:
		Set<InvoiceLineDetail> details = new HashSet<InvoiceLineDetail>()
		domain.detailLines = details
		domain.arAccount.accountType = 'AccountsReceivable'

		then:
		!domain.validate(['detailLines'])
		domain.errors["detailLines"].code == 'invoice.details.minimum'

		when:
		details.add(new InvoiceLineDetail())
		domain.detailLines = details
		domain.status = 'ADD'

		then:
		domain.validate()

		when:
		domain.status = 'UPDATE'

		then:
		domain.validate()

		when:
		domain.status = 'DELETE'

		then:
		domain.validate()
	}
}
