package ca.airspeed.wsbooks



import grails.test.mixin.*
import spock.lang.*

@TestFor(TsheetsUserXrefController)
@Mock(TsheetsUserXref)
class TsheetsUserXrefControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
		params["userName"] = 'Jack Sparrow'
		params["tsheetsUserId"] = 1234
		params["qbEntityListId"] = 'ABC-123'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.tsheetsUserXrefInstanceList
            model.tsheetsUserXrefInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.tsheetsUserXrefInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            request.contentType = FORM_CONTENT_TYPE
            def tsheetsUserXref = new TsheetsUserXref()
            tsheetsUserXref.validate()
            controller.save(tsheetsUserXref)

        then:"The create view is rendered again with the correct model"
            model.tsheetsUserXrefInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            tsheetsUserXref = new TsheetsUserXref(params)

            controller.save(tsheetsUserXref)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/tsheetsUserXref/show/1'
            controller.flash.message != null
            TsheetsUserXref.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def tsheetsUserXref = new TsheetsUserXref(params)
            controller.show(tsheetsUserXref)

        then:"A model is populated containing the domain instance"
            model.tsheetsUserXrefInstance == tsheetsUserXref
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def tsheetsUserXref = new TsheetsUserXref(params)
            controller.edit(tsheetsUserXref)

        then:"A model is populated containing the domain instance"
            model.tsheetsUserXrefInstance == tsheetsUserXref
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            request.contentType = FORM_CONTENT_TYPE
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/tsheetsUserXref/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def tsheetsUserXref = new TsheetsUserXref()
            tsheetsUserXref.validate()
            controller.update(tsheetsUserXref)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.tsheetsUserXrefInstance == tsheetsUserXref

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            tsheetsUserXref = new TsheetsUserXref(params).save(flush: true)
            controller.update(tsheetsUserXref)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/tsheetsUserXref/show/$tsheetsUserXref.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            request.contentType = FORM_CONTENT_TYPE
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/tsheetsUserXref/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def tsheetsUserXref = new TsheetsUserXref(params).save(flush: true)

        then:"It exists"
            TsheetsUserXref.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(tsheetsUserXref)

        then:"The instance is deleted"
            TsheetsUserXref.count() == 0
            response.redirectedUrl == '/tsheetsUserXref/index'
            flash.message != null
    }
}
