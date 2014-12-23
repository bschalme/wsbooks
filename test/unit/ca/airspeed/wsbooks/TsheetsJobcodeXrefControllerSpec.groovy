package ca.airspeed.wsbooks



import grails.test.mixin.*
import spock.lang.*

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

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.tsheetsJobcodeXrefInstanceList
            model.tsheetsJobcodeXrefInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.tsheetsJobcodeXrefInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def tsheetsJobcodeXref = new TsheetsJobcodeXref()
            tsheetsJobcodeXref.validate()
            controller.save(tsheetsJobcodeXref)

        then:"The create view is rendered again with the correct model"
            model.tsheetsJobcodeXrefInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            tsheetsJobcodeXref = new TsheetsJobcodeXref(params)

            controller.save(tsheetsJobcodeXref)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/tsheetsJobcodeXref/show/1'
            controller.flash.message != null
            TsheetsJobcodeXref.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def tsheetsJobcodeXref = new TsheetsJobcodeXref(params)
            controller.show(tsheetsJobcodeXref)

        then:"A model is populated containing the domain instance"
            model.tsheetsJobcodeXrefInstance == tsheetsJobcodeXref
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def tsheetsJobcodeXref = new TsheetsJobcodeXref(params)
            controller.edit(tsheetsJobcodeXref)

        then:"A model is populated containing the domain instance"
            model.tsheetsJobcodeXrefInstance == tsheetsJobcodeXref
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/tsheetsJobcodeXref/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def tsheetsJobcodeXref = new TsheetsJobcodeXref()
            tsheetsJobcodeXref.validate()
            controller.update(tsheetsJobcodeXref)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.tsheetsJobcodeXrefInstance == tsheetsJobcodeXref

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            tsheetsJobcodeXref = new TsheetsJobcodeXref(params).save(flush: true)
            controller.update(tsheetsJobcodeXref)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/tsheetsJobcodeXref/show/$tsheetsJobcodeXref.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/tsheetsJobcodeXref/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def tsheetsJobcodeXref = new TsheetsJobcodeXref(params).save(flush: true)

        then:"It exists"
            TsheetsJobcodeXref.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(tsheetsJobcodeXref)

        then:"The instance is deleted"
            TsheetsJobcodeXref.count() == 0
            response.redirectedUrl == '/tsheetsJobcodeXref/index'
            flash.message != null
    }
}
