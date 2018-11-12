package ca.airspeed.wsbooks

import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

@TestFor(TsheetsUserXrefController)
@Mock(TsheetsUserXref)
class TsheetsUserXrefControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
		params["userName"] = 'Jack Sparrow'
		params["tsheetsUserId"] = 1234
		params["qbEntityListId"] = 'ABC-123'
    }
	
    def populateInvalidParams(params) {
        assert params != null
		params["userName"] = null
		params["tsheetsUserId"] = 1234
		params["qbEntityListId"] = 'ABC-123'
    }
	
	void "Test the index action redirects to the list action"() {
		
		when:"The index action is executed"
		    controller.index()
			
		then:"We are redirected to the list action"
		    response.redirectedUrl == '/tsheetsUserXref/list'
	}

    void "Test the list action returns the correct model"() {

        when:"The list action is executed"
            def model = controller.list()

        then:"The model is correct"
            !model.tsheetsUserXrefInstanceList
            model.tsheetsUserXrefInstanceTotal == 0
    }

    void "Test the create action returns the correct model and view"() {
        when:"The create action is executed with a GET"
		    request.method = 'GET'
            def model = controller.create()

        then:"An empty model is returned"
            model.tsheetsUserXrefInstance!= null
			
		when:"We POST a well-formed model"
		    populateValidParams(params)
		    def tsheetsUserXref = new TsheetsUserXref(params)
		    response.reset()
		    request.method = 'POST'
			controller.create()
			
		then:"We are redirected to the show action for the new model"
		    response.redirectUrl?.startsWith('/tsheetsUserXref/show/') 
    }

	void "Test the create of an invalid model"() {
	    when:"An invalid model is used on a POST to create"	
		    populateInvalidParams(params)
		    request.method = 'POST'
		    def model = controller.create()
			
		then:"We are unceremoniously returned to the create view."
		    view == '/tsheetsUserXref/create'
	}

	void "Test that the show action returns the correct model"() {
        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def tsheetsUserXref = new TsheetsUserXref(params).save(flush: true)
			params.id = tsheetsUserXref.id
            def model = controller.show()

        then:"A model is populated containing the domain instance"
            model.tsheetsUserXrefInstance == tsheetsUserXref
    }

    void "Test that the edit action returns the correct model"() {
        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def tsheetsUserXref = new TsheetsUserXref(params).save(flush: true)
			params.id = tsheetsUserXref.id
			request.method = 'GET'
            def model = controller.edit()

        then:"A model is populated containing the domain instance"
            model.tsheetsUserXrefInstance == tsheetsUserXref
			
	    when:"That model is changed"
		    response.reset()
			request.method = 'POST'
		    params.tsheetsUserId = 5678
			model = controller.edit()
			
		then:"A redirect is issued to the show action"
            response.redirectedUrl == "/tsheetsUserXref/show/$tsheetsUserXref.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def tsheetsUserXref = new TsheetsUserXref(params).save(flush: true)

        then:"It exists"
            TsheetsUserXref.count() == 1

        when:"The domain instance is passed to the delete action"
		    params.id = tsheetsUserXref.id
            controller.delete()

        then:"The instance is deleted"
            TsheetsUserXref.count() == 0
            response.redirectedUrl == '/tsheetsUserXref/list'
            flash.message != null
    }
}
