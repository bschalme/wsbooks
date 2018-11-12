package ca.airspeed.wsbooks



import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

@TestFor(ControlController)
@Mock(Control)
class ControlControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["rowName"] = "Test Row"
		params["tsheetsLastFetchedDate"] = new Date()
    }

    def populateChangedParams(params) {
        assert params != null
        params["rowName"] = "Test Row"
		params["tsheetsLastFetchedDate"] = new Date()
    }

    def populateInvalidParams(params) {
        assert params != null
        params["rowName"] = null
		params["tsheetsLastFetchedDate"] = new Date()
    }
	
	void "Test the index action redirects to the list action"() {
		
		when:"The index action is executed"
		    controller.index()
			
		then:"We are redirected to the list action"
		    response.redirectedUrl == '/control/list'
	}

    void "Test the list action returns the correct model"() {

        when:"The list action is executed"
            def model = controller.list()

        then:"The model is correct"
            !model.controlInstanceList
            model.controlInstanceTotal == 0
    }

    void "Test the create action returns the correct model and view"() {
        when:"The create action is executed with a GET"
		    request.method = 'GET'
            def model = controller.create()

        then:"An empty model is returned"
            model.controlInstance!= null
			
		when:"We POST a well-formed model"
		    populateValidParams(params)
		    def control = new Control(params)
		    response.reset()
		    request.method = 'POST'
			controller.create()
			
		then:"We are redirected to the show action for the new model"
		    response.redirectUrl?.startsWith('/control/show/') 
    }

	void "Test the create of an invalid model"() {
	    when:"An invalid model is used on a POST to create"	
		    populateInvalidParams(params)
		    request.method = 'POST'
		    def model = controller.create()
			
		then:"We are unceremoniously returned to the create view."
		    view == '/control/create'
	}

	void "Test that the show action returns the correct model"() {
        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def control = new Control(params).save(flush: true)
			params.id = control.id
            def model = controller.show()

        then:"A model is populated containing the domain instance"
            model.controlInstance == control
    }

    void "Test that the edit action returns the correct model"() {
        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def control = new Control(params).save(flush: true)
			params.id = control.id
			request.method = 'GET'
            def model = controller.edit()

        then:"A model is populated containing the domain instance"
            model.controlInstance == control
			
	    when:"That model is changed"
		    response.reset()
			request.method = 'POST'
			populateChangedParams(params)
			model = controller.edit()
			
		then:"A redirect is issued to the show action"
            response.redirectedUrl == "/control/show/$control.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def control = new Control(params).save(flush: true)

        then:"It exists"
            Control.count() == 1

        when:"The domain instance is passed to the delete action"
		    params.id = control.id
            controller.delete()

        then:"The instance is deleted"
            Control.count() == 0
            response.redirectedUrl == '/control/list'
            flash.message != null
    }
}
