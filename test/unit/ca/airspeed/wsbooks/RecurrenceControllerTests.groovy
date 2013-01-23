package ca.airspeed.wsbooks



import org.junit.*

import ca.airspeed.wsbooks.Recurrence;
import ca.airspeed.wsbooks.RecurrenceController;
import grails.test.mixin.*

@TestFor(RecurrenceController)
@Mock(Recurrence)
class RecurrenceControllerTests {

    def populateValidParams(params) {
        assert params != null
        params["entityNumber"] = '347'
    }

    void testIndex() {
        controller.index()
        assert "/recurrence/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.recurrenceInstanceList.size() == 0
        assert model.recurrenceInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.recurrenceInstance != null
    }

    void testSave() {
        controller.save()

        assert model.recurrenceInstance != null
        assert view == '/recurrence/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/recurrence/show/1'
        assert controller.flash.message != null
        assert Recurrence.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/recurrence/list'

        populateValidParams(params)
        def recurrence = new Recurrence(params)

        assert recurrence.save() != null

        params.id = recurrence.id

        def model = controller.show()

        assert model.recurrenceInstance == recurrence
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/recurrence/list'

        populateValidParams(params)
        def recurrence = new Recurrence(params)

        assert recurrence.save() != null

        params.id = recurrence.id

        def model = controller.edit()

        assert model.recurrenceInstance == recurrence
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/recurrence/list'

        response.reset()

        populateValidParams(params)
        def recurrence = new Recurrence(params)

        assert recurrence.save() != null

        // test invalid parameters in update
        params.id = recurrence.id
		params.entityNumber = null

        controller.update()

        assert view == "/recurrence/edit"
        assert model.recurrenceInstance != null

        recurrence.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/recurrence/show/$recurrence.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        recurrence.clearErrors()

        populateValidParams(params)
        params.id = recurrence.id
        params.version = -1
        controller.update()

        assert view == "/recurrence/edit"
        assert model.recurrenceInstance != null
        assert model.recurrenceInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/recurrence/list'

        response.reset()

        populateValidParams(params)
        def recurrence = new Recurrence(params)

        assert recurrence.save() != null
        assert Recurrence.count() == 1

        params.id = recurrence.id

        controller.delete()

        assert Recurrence.count() == 0
        assert Recurrence.get(recurrence.id) == null
        assert response.redirectedUrl == '/recurrence/list'
    }
}
