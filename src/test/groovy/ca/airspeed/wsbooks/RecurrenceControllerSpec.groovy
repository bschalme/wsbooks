package ca.airspeed.wsbooks



import grails.test.mixin.TestFor
import grails.test.mixin.Mock
import spock.lang.Specification

@TestFor(RecurrenceController)
@Mock(Recurrence)
class RecurrenceControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        params["nickname"] = 'Website Hosting for MegaCorp'
        params["entityName"] = 'Invoice'
        params["entityNumber"] = '47'
        params["frequency"] = 'Monthly'
        params["txnTiming"] = 'In Advance'
        params["active"] = true
    }

    def populateChangedParams(params) {
        assert params != null
        params["nickname"] = 'Website Hosting for SmallBiz'
        params["entityName"] = 'Invoice'
        params["entityNumber"] = '58'
        params["frequency"] = 'Monthly'
        params["txnTiming"] = 'In Advance'
        params["active"] = true
    }

    def populateInvalidParams(params) {
        assert params != null
        params["nickname"] = 'Website Hosting for MegaCorp'
        params["entityName"] = 'Cheeseburger'
        params["entityNumber"] = '47'
        params["frequency"] = 'Monthly'
        params["txnTiming"] = 'In Advance'
        params["active"] = true
    }
	
	void "Test the index action redirects to the list action"() {
		
		when:"The index action is executed"
		    controller.index()
			
		then:"We are redirected to the list action"
		    response.redirectedUrl == '/recurrence/list'
	}

    void "Test the list action returns the correct model"() {

        when:"The list action is executed"
            def model = controller.list()

        then:"The model is correct"
            !model.recurrenceInstanceList
            model.recurrenceInstanceTotal == 0
    }

    void "Test the create action returns the correct model and view"() {
        when:"The create action is executed with a GET"
		    request.method = 'GET'
            def model = controller.create()

        then:"An empty model is returned"
            model.recurrenceInstance!= null
			
		when:"We POST a well-formed model"
		    populateValidParams(params)
		    def recurrence = new Recurrence(params)
		    response.reset()
		    request.method = 'POST'
			controller.create()
			
		then:"We are redirected to the show action for the new model"
		    response.redirectUrl?.startsWith('/recurrence/show/') 
    }

	void "Test the create of an invalid model"() {
	    when:"An invalid model is used on a POST to create"	
		    populateInvalidParams(params)
		    request.method = 'POST'
		    def model = controller.create()
			
		then:"We are unceremoniously returned to the create view."
		    view == '/recurrence/create'
	}

	void "Test that the show action returns the correct model"() {
        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def recurrence = new Recurrence(params).save(flush: true)
			params.id = recurrence.id
            def model = controller.show()

        then:"A model is populated containing the domain instance"
            model.recurrenceInstance == recurrence
    }

    void "Test that the edit action returns the correct model"() {
        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def recurrence = new Recurrence(params).save(flush: true)
			params.id = recurrence.id
			request.method = 'GET'
            def model = controller.edit()

        then:"A model is populated containing the domain instance"
            model.recurrenceInstance == recurrence
			
	    when:"That model is changed"
		    response.reset()
			request.method = 'POST'
			populateChangedParams(params)
			model = controller.edit()
			
		then:"A redirect is issued to the show action"
            response.redirectedUrl == "/recurrence/show/$recurrence.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def recurrence = new Recurrence(params).save(flush: true)

        then:"It exists"
            Recurrence.count() == 1

        when:"The domain instance is passed to the delete action"
		    params.id = recurrence.id
            controller.delete()

        then:"The instance is deleted"
            Recurrence.count() == 0
            response.redirectedUrl == '/recurrence/list'
            flash.message != null
    }
}
