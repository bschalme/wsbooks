package ca.airspeed.wsbooks

import org.springframework.dao.DataIntegrityViolationException

class ControlController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [controlInstanceList: Control.list(params), controlInstanceTotal: Control.count()]
    }

    def create() {
        [controlInstance: new Control(params)]
    }

    def save() {
        def controlInstance = new Control(params)
        if (!controlInstance.save(flush: true)) {
            render(view: "create", model: [controlInstance: controlInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'control.label', default: 'Control'), controlInstance.id])
        redirect(action: "show", id: controlInstance.id)
    }

    def show(Long id) {
        def controlInstance = Control.get(id)
        if (!controlInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'control.label', default: 'Control'), id])
            redirect(action: "list")
            return
        }

        [controlInstance: controlInstance]
    }

    def edit(Long id) {
        def controlInstance = Control.get(id)
        if (!controlInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'control.label', default: 'Control'), id])
            redirect(action: "list")
            return
        }

        [controlInstance: controlInstance]
    }

    def update(Long id, Long version) {
        def controlInstance = Control.get(id)
        if (!controlInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'control.label', default: 'Control'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (controlInstance.version > version) {
                controlInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'control.label', default: 'Control')] as Object[],
                          "Another user has updated this Control while you were editing")
                render(view: "edit", model: [controlInstance: controlInstance])
                return
            }
        }

        controlInstance.properties = params

        if (!controlInstance.save(flush: true)) {
            render(view: "edit", model: [controlInstance: controlInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'control.label', default: 'Control'), controlInstance.id])
        redirect(action: "show", id: controlInstance.id)
    }

    def delete(Long id) {
        def controlInstance = Control.get(id)
        if (!controlInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'control.label', default: 'Control'), id])
            redirect(action: "list")
            return
        }

        try {
            controlInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'control.label', default: 'Control'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'control.label', default: 'Control'), id])
            redirect(action: "show", id: id)
        }
    }
}
