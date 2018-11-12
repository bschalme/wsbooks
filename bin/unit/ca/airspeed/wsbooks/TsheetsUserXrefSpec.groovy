package ca.airspeed.wsbooks

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(TsheetsUserXref)
class TsheetsUserXrefSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "Test the userName must have a non-empty String"() {
		when: 'the userName is null'
		def user = new TsheetsUserXref(tsheetsUserId: 0, qbEntityListId: "ABC-123")
		
		then: 'validation should fail'
		!user.validate()
		
		when: 'the userName is an empty String'
		user = new TsheetsUserXref(userName: "", tsheetsUserId: 0, qbEntityListId: "ABC-123")
		
		then: 'validation should fail'
		!user.validate()
		
		when: 'the userName is a String'
		user = new TsheetsUserXref(userName: "Jack Sparrow", tsheetsUserId: 0, qbEntityListId: "ABC-123")
		
		then: 'validation should pass'
		user.validate()
    }
	
	void "Test the tsheetsUserId is present"() {
		when: 'tsheetsUserId is null'
		def user = new TsheetsUserXref(userName: "Jack Sparrow", tsheetsUserId: null, qbEntityListId: "ABC-123")
		
		then: 'validation should fail'
		!user.validate()

		when: 'tsheetsUserId has a value'
		user = new TsheetsUserXref(userName: "Jack Sparrow", tsheetsUserId: 789, qbEntityListId: "ABC-123")
		
		then: 'validation should pass'
		user.validate()
	}

	    void "Test the qbEntityListId must have a non-empty String"() {
		when: 'the qbEntityListId is null'
		def user = new TsheetsUserXref(userName: "Jack Sparrow", tsheetsUserId: 0, qbEntityListId: null)
		
		then: 'validation should fail'
		!user.validate()
		
		when: 'the qbEntityListId is an empty String'
		user = new TsheetsUserXref(userName: "Jack Sparrow", tsheetsUserId: 0, qbEntityListId: "")
		
		then: 'validation should fail'
		!user.validate()
		
		when: 'the qbEntityListId is a String'
		user = new TsheetsUserXref(userName: "Jack Sparrow", tsheetsUserId: 0, qbEntityListId: "ABC-123")
		
		then: 'validation should pass'
		user.validate()
    }
	
}
