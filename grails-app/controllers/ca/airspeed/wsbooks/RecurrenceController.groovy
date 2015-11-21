package ca.airspeed.wsbooks

import org.springframework.dao.DataIntegrityViolationException

class RecurrenceController {

	static scaffold = Recurrence

    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [recurrenceInstanceList: Recurrence.list(params), recurrenceInstanceTotal: Recurrence.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[recurrenceInstance: new Recurrence(params)]
			break
		case 'POST':
	        def recurrenceInstance = new Recurrence(params)
	        if (!recurrenceInstance.save(flush: true)) {
	            render view: 'create', model: [recurrenceInstance: recurrenceInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), recurrenceInstance.id])
	        redirect action: 'show', id: recurrenceInstance.id
			break
		}
    }

    def show() {
        def recurrenceInstance = Recurrence.get(params.id)
        if (!recurrenceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), params.id])
            redirect action: 'list'
            return
        }

        [recurrenceInstance: recurrenceInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def recurrenceInstance = Recurrence.get(params.id)
	        if (!recurrenceInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [recurrenceInstance: recurrenceInstance]
			break
		case 'POST':
	        def recurrenceInstance = Recurrence.get(params.id)
	        if (!recurrenceInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (recurrenceInstance.version > version) {
	                recurrenceInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'recurrence.label', default: 'Recurrence')] as Object[],
	                          "Another user has updated this Recurrence while you were editing")
	                render view: 'edit', model: [recurrenceInstance: recurrenceInstance]
	                return
	            }
	        }

	        recurrenceInstance.properties = params

	        if (!recurrenceInstance.save(flush: true)) {
	            render view: 'edit', model: [recurrenceInstance: recurrenceInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), recurrenceInstance.id])
	        redirect action: 'show', id: recurrenceInstance.id
			break
		}
    }

    def delete() {
        def recurrenceInstance = Recurrence.get(params.id)
        if (!recurrenceInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), params.id])
            redirect action: 'list'
            return
        }

        try {
            recurrenceInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}