package ca.airspeed.wsbooks

import static org.springframework.http.HttpStatus.CREATED

import java.util.Map;

import grails.artefact.Artefact;
import grails.rest.RestfulController
import grails.transaction.Transactional;

import org.codehaus.groovy.grails.web.servlet.HttpHeaders
import org.springframework.http.HttpStatus

@Artefact("Controller")
@Transactional(readOnly = false)
class CreditCardChargeController extends RestfulController<CreditCardCharge> {
    static allowedMethods = [show: "GET", save: "POST", update: "PUT", delete: "DELETE"]
	static responseFormats = ['json', 'xml']

	CreditCardChargeController() {
		super(CreditCardCharge)
	}
	
    /**
     * Saves a resource
     */
    @Transactional
	@Override
    def save() {
        if(handleReadOnly()) {
            return
        }
        def instance = createResource(getParametersToBind())
		instance.status = 'ADD'

        instance.validate()
        if (instance.hasErrors()) {
            respond instance.errors, view:'create' // STATUS CODE 422
            return
        }
		instance.expenseLines?.each { txnExpenselineDetail ->
			txnExpenselineDetail.creditCardCharge = instance
			txnExpenselineDetail.validate()
			if (txnExpenselineDetail.hasErrors()) {
				respond txnExpenselineDetail.errors, view:'create' // STATUS CODE 422
				return
			}
		}

        instance.save flush:true
		instance.expenseLines?.each { txnExpenselineDetail ->
			txnExpenselineDetail.save (flush:true)
		}

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: "${resourceName}.label".toString(), default: resourceClassName), instance.id])
                redirect instance
            }
            '*' {
                response.addHeader(HttpHeaders.LOCATION,
                        g.createLink(
                                resource: this.controllerName, action: 'show',id: instance.id, absolute: true,
                                namespace: hasProperty('namespace') ? this.namespace : null ))
                respond instance, [status: CREATED]
            }
        }
    }

	@Override
	protected Map getParametersToBind() {
		return request.JSON
	}


}
