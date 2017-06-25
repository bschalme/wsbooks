class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?(.$format)?"{
			constraints {
				// apply constraints here
			}
		}

		"/api/CreditCardCharges"(resources:'CreditCardCharge') {
			"/TxnExpenseLineDetails"(resources:'TxnExpenseLineDetail')
		}
		"/"(view:"/index")
		"500"(view:'/error')
	}
}
