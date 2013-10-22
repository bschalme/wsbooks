package ca.airspeed.wsbooks

import org.springframework.dao.DataIntegrityViolationException

import ca.airspeed.wsbooks.Recurrence;

class RecurrenceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [recurrenceInstanceList: Recurrence.list(params), recurrenceInstanceTotal: Recurrence.count()]
    }

    def create() {
        [recurrenceInstance: new Recurrence(params)]
    }

    def save() {
        def recurrenceInstance = new Recurrence(params)
        if (!recurrenceInstance.save(flush: true)) {
            render(view: "create", model: [recurrenceInstance: recurrenceInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), recurrenceInstance.id])
        redirect(action: "show", id: recurrenceInstance.id)
    }

    def show(Long id) {
        def recurrenceInstance = Recurrence.get(id)
        if (!recurrenceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), id])
            redirect(action: "list")
            return
        }

        [recurrenceInstance: recurrenceInstance]
    }

    def edit(Long id) {
        def recurrenceInstance = Recurrence.get(id)
        if (!recurrenceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), id])
            redirect(action: "list")
            return
        }

        [recurrenceInstance: recurrenceInstance]
    }

    def update(Long id, Long version) {
        def recurrenceInstance = Recurrence.get(id)
        if (!recurrenceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (recurrenceInstance.version > version) {
                recurrenceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'recurrence.label', default: 'Recurrence')] as Object[],
                          "Another user has updated this Recurrence while you were editing")
                render(view: "edit", model: [recurrenceInstance: recurrenceInstance])
                return
            }
        }

        recurrenceInstance.properties = params

        if (!recurrenceInstance.save(flush: true)) {
            render(view: "edit", model: [recurrenceInstance: recurrenceInstance])
            return
        }

    	log.info('Wrote a Recurrence for ' + recurrenceInstance.entityName + ' ' + recurrenceInstance.entityNumber + ' on a ' + recurrenceInstance.frequency + ' basis.')
		// log.info('Hello World!')
        flash.message = message(code: 'default.updated.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), recurrenceInstance.id])
        redirect(action: "show", id: recurrenceInstance.id)
    }

    def delete(Long id) {
        def recurrenceInstance = Recurrence.get(id)
        if (!recurrenceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), id])
            redirect(action: "list")
            return
        }

        try {
            recurrenceInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'recurrence.label', default: 'Recurrence'), id])
            redirect(action: "show", id: id)
        }
    }
}
