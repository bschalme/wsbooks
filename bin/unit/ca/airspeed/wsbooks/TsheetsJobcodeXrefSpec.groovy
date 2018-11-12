package ca.airspeed.wsbooks

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TsheetsJobcodeXref)
class TsheetsJobcodeXrefSpec extends Specification {

	def setup() {
	}

	def cleanup() {
	}

	void "Test the tsheetsJobcodeId has a value"() {
		when: 'tsheetsJobcodeId is null'
		def jobCode = new TsheetsJobcodeXref(jobName: 'The Job', tsheetsJobcodeId: null, qbCustomerListId: 'ABC-123', qbItemServiceListId: 'DEF-456')

		then: 'validation should fail'
		!jobCode.validate()

		when: 'tsheetsJobcodeId has a value'
		jobCode = new TsheetsJobcodeXref(jobName: 'The Job', tsheetsJobcodeId: 25, qbCustomerListId: 'ABC-123', qbItemServiceListId: 'DEF-456')

		then: 'validation should pass'
		jobCode.validate()
	}

	void "Test the qbCustomerListId has a non-empty String"() {
		when: 'qbCustomerListId is null'
		def jobCode = new TsheetsJobcodeXref(jobName: 'The Job', tsheetsJobcodeId: 25, qbCustomerListId: null, qbItemServiceListId: 'DEF-456')

		then: 'validation should fail'
		!jobCode.validate()

		when: 'qbCustomerListId is blank'
		jobCode = new TsheetsJobcodeXref(jobName: 'The Job', tsheetsJobcodeId: 25, qbCustomerListId: " ", qbItemServiceListId: 'DEF-456')

		then: 'validation should fail'
		!jobCode.validate()

		when: 'qbCustomerListId is a non-empty String'
		jobCode = new TsheetsJobcodeXref(jobName: 'The Job', tsheetsJobcodeId: 25, qbCustomerListId: "ABC-123", qbItemServiceListId: 'DEF-456')

		then: 'validation should pass'
		jobCode.validate()
	}

	void "Test the qbItemServiceListId has a non-empty String" () {
		when: 'qbItemServiceListId is null'
		def jobCode = new TsheetsJobcodeXref(jobName: 'The Job', tsheetsJobcodeId: 25, qbCustomerListId: "ABC-123", qbItemServiceListId: null)

		then: 'validation should fail'
		!jobCode.validate()

		when: 'qbItemServiceListId is blank'
		jobCode = new TsheetsJobcodeXref(jobName: 'The Job', tsheetsJobcodeId: 25, qbCustomerListId: "ABC-123", qbItemServiceListId: ' ')

		then: 'validation should fail'
		!jobCode.validate()

		when: 'qbItemServiceListId is a non-empty String'
		jobCode = new TsheetsJobcodeXref(jobName: 'The Job', tsheetsJobcodeId: 25, qbCustomerListId: "ABC-123", qbItemServiceListId: 'DEF-456')

		then: 'validation should pass'
		jobCode.validate()
	}
}
