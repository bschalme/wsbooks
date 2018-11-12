package ca.airspeed.wsbooks



import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

@TestFor(TsheetsJobcodeXrefController)
@Mock(TsheetsJobcodeXref)
class TsheetsJobcodeXrefControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
		params["jobName"] = 'The Big Job'
		params["tsheetsJobcodeId"] = 4077
		params["qbCustomerListId"] = 'ABC-123'
		params["qbItemServiceListId"] = 'DEF-456'
    }

    def populateChangedParams(params) {
        assert params != null
		params["jobName"] = 'The Little Job'
		params["tsheetsJobcodeId"] = 4077
		params["qbCustomerListId"] = 'ABC-123'
		params["qbItemServiceListId"] = 'DEF-456'
    }

    def populateInvalidParams(params) {
        assert params != null
		params["jobName"] = null
		params["tsheetsJobcodeId"] = 4077
		params["qbCustomerListId"] = 'ABC-123'
		params["qbItemServiceListId"] = 'DEF-456'
    }
	
	void "Test the index action redirects to the list action"() {
		
		when:"The index action is executed"
		    controller.index()
			
		then:"We are redirected to the list action"
		    response.redirectedUrl == '/tsheetsJobcodeXref/list'
	}

    void "Test the list action returns the correct model"() {

        when:"The list action is executed"
            def model = controller.list()

        then:"The model is correct"
            !model.tsheetsJobcodeXrefInstanceList
            model.tsheetsJobcodeXrefInstanceTotal == 0
    }

    void "Test the create action returns the correct model and view"() {
        when:"The create action is executed with a GET"
		    request.method = 'GET'
            def model = controller.create()

        then:"An empty model is returned"
            model.tsheetsJobcodeXrefInstance!= null
			
		when:"We POST a well-formed model"
		    populateValidParams(params)
		    def tsheetsJobcodeXref = new TsheetsJobcodeXref(params)
		    response.reset()
		    request.method = 'POST'
			controller.create()
			
		then:"We are redirected to the show action for the new model"
		    response.redirectUrl?.startsWith('/tsheetsJobcodeXref/show/') 
    }

	void "Test the create of an invalid model"() {
	    when:"An invalid model is used on a POST to create"	
		    populateInvalidParams(params)
		    request.method = 'POST'
		    def model = controller.create()
			
		then:"We are unceremoniously returned to the create view."
		    view == '/tsheetsJobcodeXref/create'
	}

	void "Test that the show action returns the correct model"() {
        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def tsheetsJobcodeXref = new TsheetsJobcodeXref(params).save(flush: true)
			params.id = tsheetsJobcodeXref.id
            def model = controller.show()

        then:"A model is populated containing the domain instance"
            model.tsheetsJobcodeXrefInstance == tsheetsJobcodeXref
    }

    void "Test that the edit action returns the correct model"() {
        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def tsheetsJobcodeXref = new TsheetsJobcodeXref(params).save(flush: true)
			params.id = tsheetsJobcodeXref.id
			request.method = 'GET'
            def model = controller.edit()

        then:"A model is populated containing the domain instance"
            model.tsheetsJobcodeXrefInstance == tsheetsJobcodeXref
			
	    when:"That model is changed"
		    response.reset()
			request.method = 'POST'
			populateChangedParams(params)
			model = controller.edit()
			
		then:"A redirect is issued to the show action"
            response.redirectedUrl == "/tsheetsJobcodeXref/show/$tsheetsJobcodeXref.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def tsheetsJobcodeXref = new TsheetsJobcodeXref(params).save(flush: true)

        then:"It exists"
            TsheetsJobcodeXref.count() == 1

        when:"The domain instance is passed to the delete action"
		    params.id = tsheetsJobcodeXref.id
            controller.delete()

        then:"The instance is deleted"
            TsheetsJobcodeXref.count() == 0
            response.redirectedUrl == '/tsheetsJobcodeXref/list'
            flash.message != null
    }
}
