package ca.airspeed.wsbooks

import org.springframework.dao.DataIntegrityViolationException

class ControlController {

	static scaffold = Control
	static Boolean linkMe = true
	
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [controlInstanceList: Control.list(params), controlInstanceTotal: Control.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[controlInstance: new Control(params)]
			break
		case 'POST':
	        def controlInstance = new Control(params)
	        if (!controlInstance.save(flush: true)) {
	            render view: 'create', model: [controlInstance: controlInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'control.label', default: 'Control'), controlInstance.id])
	        redirect action: 'show', id: controlInstance.id
			break
		}
    }

    def show() {
        def controlInstance = Control.get(params.id)
        if (!controlInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'control.label', default: 'Control'), params.id])
            redirect action: 'list'
            return
        }

        [controlInstance: controlInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def controlInstance = Control.get(params.id)
	        if (!controlInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'control.label', default: 'Control'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [controlInstance: controlInstance]
			break
		case 'POST':
	        def controlInstance = Control.get(params.id)
	        if (!controlInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'control.label', default: 'Control'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (controlInstance.version > version) {
	                controlInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'control.label', default: 'Control')] as Object[],
	                          "Another user has updated this Control while you were editing")
	                render view: 'edit', model: [controlInstance: controlInstance]
	                return
	            }
	        }

	        controlInstance.properties = params

	        if (!controlInstance.save(flush: true)) {
	            render view: 'edit', model: [controlInstance: controlInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'control.label', default: 'Control'), controlInstance.id])
	        redirect action: 'show', id: controlInstance.id
			break
		}
    }

    def delete() {
        def controlInstance = Control.get(params.id)
        if (!controlInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'control.label', default: 'Control'), params.id])
            redirect action: 'list'
            return
        }

        try {
            controlInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'control.label', default: 'Control'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'control.label', default: 'Control'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}