package ca.airspeed.wsbooks



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TsheetsUserXrefController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond TsheetsUserXref.list(params), model:[tsheetsUserXrefInstanceCount: TsheetsUserXref.count()]
    }

    def show(TsheetsUserXref tsheetsUserXrefInstance) {
        respond tsheetsUserXrefInstance
    }

    def create() {
        respond new TsheetsUserXref(params)
    }

    @Transactional
    def save(TsheetsUserXref tsheetsUserXrefInstance) {
        if (tsheetsUserXrefInstance == null) {
            notFound()
            return
        }

        if (tsheetsUserXrefInstance.hasErrors()) {
            respond tsheetsUserXrefInstance.errors, view:'create'
            return
        }

        tsheetsUserXrefInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tsheetsUserXref.label', default: 'TsheetsUserXref'), tsheetsUserXrefInstance.id])
                redirect tsheetsUserXrefInstance
            }
            '*' { respond tsheetsUserXrefInstance, [status: CREATED] }
        }
    }

    def edit(TsheetsUserXref tsheetsUserXrefInstance) {
        respond tsheetsUserXrefInstance
    }

    @Transactional
    def update(TsheetsUserXref tsheetsUserXrefInstance) {
        if (tsheetsUserXrefInstance == null) {
            notFound()
            return
        }

        if (tsheetsUserXrefInstance.hasErrors()) {
            respond tsheetsUserXrefInstance.errors, view:'edit'
            return
        }

        tsheetsUserXrefInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'TsheetsUserXref.label', default: 'TsheetsUserXref'), tsheetsUserXrefInstance.id])
                redirect tsheetsUserXrefInstance
            }
            '*'{ respond tsheetsUserXrefInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(TsheetsUserXref tsheetsUserXrefInstance) {

        if (tsheetsUserXrefInstance == null) {
            notFound()
            return
        }

        tsheetsUserXrefInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'TsheetsUserXref.label', default: 'TsheetsUserXref'), tsheetsUserXrefInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tsheetsUserXref.label', default: 'TsheetsUserXref'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
