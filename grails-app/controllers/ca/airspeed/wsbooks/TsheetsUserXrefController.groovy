package ca.airspeed.wsbooks

import grails.plugin.springsecurity.annotation.Secured;

import org.springframework.dao.DataIntegrityViolationException

@Secured('ROLE_ADMIN')
class TsheetsUserXrefController {

	static scaffold = TsheetsUserXref
	static Boolean linkMe = true
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tsheetsUserXrefInstanceList: TsheetsUserXref.list(params), tsheetsUserXrefInstanceTotal: TsheetsUserXref.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[tsheetsUserXrefInstance: new TsheetsUserXref(params)]
			break
		case 'POST':
	        def tsheetsUserXrefInstance = new TsheetsUserXref(params)
	        if (!tsheetsUserXrefInstance.save(flush: true)) {
	            render view: 'create', model: [tsheetsUserXrefInstance: tsheetsUserXrefInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'tsheetsUserXref.label', default: 'TsheetsUserXref'), tsheetsUserXrefInstance.id])
	        redirect action: 'show', id: tsheetsUserXrefInstance.id
			break
		}
    }

    def show() {
        def tsheetsUserXrefInstance = TsheetsUserXref.get(params.id)
        if (!tsheetsUserXrefInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tsheetsUserXref.label', default: 'TsheetsUserXref'), params.id])
            redirect action: 'list'
            return
        }

        [tsheetsUserXrefInstance: tsheetsUserXrefInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def tsheetsUserXrefInstance = TsheetsUserXref.get(params.id)
	        if (!tsheetsUserXrefInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tsheetsUserXref.label', default: 'TsheetsUserXref'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [tsheetsUserXrefInstance: tsheetsUserXrefInstance]
			break
		case 'POST':
	        def tsheetsUserXrefInstance = TsheetsUserXref.get(params.id)
	        if (!tsheetsUserXrefInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tsheetsUserXref.label', default: 'TsheetsUserXref'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (tsheetsUserXrefInstance.version > version) {
	                tsheetsUserXrefInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'tsheetsUserXref.label', default: 'TsheetsUserXref')] as Object[],
	                          "Another user has updated this TsheetsUserXref while you were editing")
	                render view: 'edit', model: [tsheetsUserXrefInstance: tsheetsUserXrefInstance]
	                return
	            }
	        }

	        tsheetsUserXrefInstance.properties = params

	        if (!tsheetsUserXrefInstance.save(flush: true)) {
	            render view: 'edit', model: [tsheetsUserXrefInstance: tsheetsUserXrefInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'tsheetsUserXref.label', default: 'TsheetsUserXref'), tsheetsUserXrefInstance.id])
	        redirect action: 'show', id: tsheetsUserXrefInstance.id
			break
		}
    }

    def delete() {
        def tsheetsUserXrefInstance = TsheetsUserXref.get(params.id)
        if (!tsheetsUserXrefInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tsheetsUserXref.label', default: 'TsheetsUserXref'), params.id])
            redirect action: 'list'
            return
        }

        try {
            tsheetsUserXrefInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tsheetsUserXref.label', default: 'TsheetsUserXref'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tsheetsUserXref.label', default: 'TsheetsUserXref'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}