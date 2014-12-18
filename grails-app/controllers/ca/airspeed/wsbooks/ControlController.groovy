package ca.airspeed.wsbooks



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ControlController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Control.list(params), model:[controlInstanceCount: Control.count()]
    }

    def show(Control controlInstance) {
        respond controlInstance
    }

    def create() {
        respond new Control(params)
    }

    @Transactional
    def save(Control controlInstance) {
        if (controlInstance == null) {
            notFound()
            return
        }

        if (controlInstance.hasErrors()) {
            respond controlInstance.errors, view:'create'
            return
        }

        controlInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'control.label', default: 'Control'), controlInstance.id])
                redirect controlInstance
            }
            '*' { respond controlInstance, [status: CREATED] }
        }
    }

    def edit(Control controlInstance) {
        respond controlInstance
    }

    @Transactional
    def update(Control controlInstance) {
        if (controlInstance == null) {
            notFound()
            return
        }

        if (controlInstance.hasErrors()) {
            respond controlInstance.errors, view:'edit'
            return
        }

        controlInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Control.label', default: 'Control'), controlInstance.id])
                redirect controlInstance
            }
            '*'{ respond controlInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Control controlInstance) {

        if (controlInstance == null) {
            notFound()
            return
        }

        controlInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Control.label', default: 'Control'), controlInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'control.label', default: 'Control'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
