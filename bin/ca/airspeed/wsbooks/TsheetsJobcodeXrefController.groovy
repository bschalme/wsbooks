package ca.airspeed.wsbooks

import grails.plugin.springsecurity.annotation.Secured;

import org.springframework.dao.DataIntegrityViolationException

@Secured('ROLE_ADMIN')
class TsheetsJobcodeXrefController {

	static scaffold = TsheetsJobcodeXref
	static Boolean linkMe = true
    static allowedMethods = [create: ['GET', 'POST'], edit: ['GET', 'POST'], delete: 'POST']

    def index() {
        redirect action: 'list', params: params
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [tsheetsJobcodeXrefInstanceList: TsheetsJobcodeXref.list(params), tsheetsJobcodeXrefInstanceTotal: TsheetsJobcodeXref.count()]
    }

    def create() {
		switch (request.method) {
		case 'GET':
        	[tsheetsJobcodeXrefInstance: new TsheetsJobcodeXref(params)]
			break
		case 'POST':
	        def tsheetsJobcodeXrefInstance = new TsheetsJobcodeXref(params)
	        if (!tsheetsJobcodeXrefInstance.save(flush: true)) {
	            render view: 'create', model: [tsheetsJobcodeXrefInstance: tsheetsJobcodeXrefInstance]
	            return
	        }

			flash.message = message(code: 'default.created.message', args: [message(code: 'tsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref'), tsheetsJobcodeXrefInstance.id])
	        redirect action: 'show', id: tsheetsJobcodeXrefInstance.id
			break
		}
    }

    def show() {
        def tsheetsJobcodeXrefInstance = TsheetsJobcodeXref.get(params.id)
        if (!tsheetsJobcodeXrefInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref'), params.id])
            redirect action: 'list'
            return
        }

        [tsheetsJobcodeXrefInstance: tsheetsJobcodeXrefInstance]
    }

    def edit() {
		switch (request.method) {
		case 'GET':
	        def tsheetsJobcodeXrefInstance = TsheetsJobcodeXref.get(params.id)
	        if (!tsheetsJobcodeXrefInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref'), params.id])
	            redirect action: 'list'
	            return
	        }

	        [tsheetsJobcodeXrefInstance: tsheetsJobcodeXrefInstance]
			break
		case 'POST':
	        def tsheetsJobcodeXrefInstance = TsheetsJobcodeXref.get(params.id)
	        if (!tsheetsJobcodeXrefInstance) {
	            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref'), params.id])
	            redirect action: 'list'
	            return
	        }

	        if (params.version) {
	            def version = params.version.toLong()
	            if (tsheetsJobcodeXrefInstance.version > version) {
	                tsheetsJobcodeXrefInstance.errors.rejectValue('version', 'default.optimistic.locking.failure',
	                          [message(code: 'tsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref')] as Object[],
	                          "Another user has updated this TsheetsJobcodeXref while you were editing")
	                render view: 'edit', model: [tsheetsJobcodeXrefInstance: tsheetsJobcodeXrefInstance]
	                return
	            }
	        }

	        tsheetsJobcodeXrefInstance.properties = params

	        if (!tsheetsJobcodeXrefInstance.save(flush: true)) {
	            render view: 'edit', model: [tsheetsJobcodeXrefInstance: tsheetsJobcodeXrefInstance]
	            return
	        }

			flash.message = message(code: 'default.updated.message', args: [message(code: 'tsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref'), tsheetsJobcodeXrefInstance.id])
	        redirect action: 'show', id: tsheetsJobcodeXrefInstance.id
			break
		}
    }

    def delete() {
        def tsheetsJobcodeXrefInstance = TsheetsJobcodeXref.get(params.id)
        if (!tsheetsJobcodeXrefInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'tsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref'), params.id])
            redirect action: 'list'
            return
        }

        try {
            tsheetsJobcodeXrefInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'tsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref'), params.id])
            redirect action: 'list'
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tsheetsJobcodeXref.label', default: 'TsheetsJobcodeXref'), params.id])
            redirect action: 'show', id: params.id
        }
    }
}