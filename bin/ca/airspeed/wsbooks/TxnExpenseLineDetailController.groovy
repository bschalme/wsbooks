package ca.airspeed.wsbooks

import java.util.Map;

import grails.plugin.springsecurity.annotation.Secured;
import grails.rest.RestfulController

@Secured('ROLE_USER')
class TxnExpenseLineDetailController extends
RestfulController<TxnExpenseLineDetail> {
	static responseFormats = ['json', 'xml']

	TxnExpenseLineDetailController() {
		super(TxnExpenseLineDetail)
	}

	@Override
	protected Map getParametersToBind() {
		println request.JSON.id
		return request.JSON
	}
}
