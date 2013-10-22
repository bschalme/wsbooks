package ca.airspeed.wsbooks



import org.junit.*
import grails.test.mixin.*

@TestFor(ControlController)
@Mock(Control)
class ControlControllerTests {

    def populateValidParams(params) {
        assert params != null
        params["tsheetsLastFetchedDate"] = new Date()
    }

    void testIndex() {
        controller.index()
        assert "/control/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.controlInstanceList.size() == 0
        assert model.controlInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.controlInstance != null
    }

    void testSave() {
        controller.save()

        assert model.controlInstance != null
        assert view == '/control/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/control/show/1'
        assert controller.flash.message != null
        assert Control.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/control/list'

        populateValidParams(params)
        def control = new Control(params)

        assert control.save() != null

        params.id = control.id

        def model = controller.show()

        assert model.controlInstance == control
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/control/list'

        populateValidParams(params)
        def control = new Control(params)

        assert control.save() != null

        params.id = control.id

        def model = controller.edit()

        assert model.controlInstance == control
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/control/list'

        response.reset()

        populateValidParams(params)
        def control = new Control(params)

        assert control.save() != null

        // test invalid parameters in update
        params.id = control.id
        params.tsheetsLastFetchedDate = null

        controller.update()

        assert view == "/control/edit"
        assert model.controlInstance != null

        control.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/control/show/$control.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        control.clearErrors()

        populateValidParams(params)
        params.id = control.id
        params.version = -1
        controller.update()

        assert view == "/control/edit"
        assert model.controlInstance != null
        assert model.controlInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/control/list'

        response.reset()

        populateValidParams(params)
        def control = new Control(params)

        assert control.save() != null
        assert Control.count() == 1

        params.id = control.id

        controller.delete()

        assert Control.count() == 0
        assert Control.get(control.id) == null
        assert response.redirectedUrl == '/control/list'
    }
}
