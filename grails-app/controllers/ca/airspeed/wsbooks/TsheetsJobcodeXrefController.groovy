package ca.airspeed.wsbooks



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TsheetsJobcodeXrefController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TsheetsJobcodeXref.list(params), model:[tsheetsJobcodeXrefInstanceCount: TsheetsJobcodeXref.count()]
    }

    def show(TsheetsJobcodeXref tsheetsJobcodeXrefInstance) {
        respond tsheetsJobcodeXrefInstance
    }

    def create() {
        respond new TsheetsJobcodeXref(params)
    }

    @Transactional
    def save(TsheetsJobcodeXref tsheetsJobcodeXrefInstance) {
        if (tsheetsJobcodeXrefInstance == null) {
            notFound()
            return
        }

        if (tsheetsJobcodeXrefInstance.hasErrors()) {
            respond tsheetsJobcodeXrefInstance.errors, view:'create'
            return
        }

        tsheetsJobcodeXrefInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref'), tsheetsJobcodeXrefInstance.id])
                redirect tsheetsJobcodeXrefInstance
            }
            '*' { respond tsheetsJobcodeXrefInstance, [status: CREATED] }
        }
    }

    def edit(TsheetsJobcodeXref tsheetsJobcodeXrefInstance) {
        respond tsheetsJobcodeXrefInstance
    }

    @Transactional
    def update(TsheetsJobcodeXref tsheetsJobcodeXrefInstance) {
        if (tsheetsJobcodeXrefInstance == null) {
            notFound()
            return
        }

        if (tsheetsJobcodeXrefInstance.hasErrors()) {
            respond tsheetsJobcodeXrefInstance.errors, view:'edit'
            return
        }

        tsheetsJobcodeXrefInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref'), tsheetsJobcodeXrefInstance.id])
                redirect tsheetsJobcodeXrefInstance
            }
            '*'{ respond tsheetsJobcodeXrefInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TsheetsJobcodeXref tsheetsJobcodeXrefInstance) {

        if (tsheetsJobcodeXrefInstance == null) {
            notFound()
            return
        }

        tsheetsJobcodeXrefInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref'), tsheetsJobcodeXrefInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
