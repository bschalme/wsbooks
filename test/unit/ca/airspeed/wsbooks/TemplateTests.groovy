package ca.airspeed.wsbooks;

import java.lang.ref.ReferenceQueue.Null;

import grails.test.mixin.TestFor;

@TestFor(Template)
class TemplateTests {

	void testConstraints() {
		Template templ = new Template()
		
		assert !templ.validate()
		assert "nullable" == templ.errors["listID"].code
		assert "nullable" == templ.errors["name"].code
		assert "nullable" == templ.errors["isActive"].code
		assert "nullable" == templ.errors["templateType"].code
		assert "nullable" == templ.errors["status"].code
		
		templ.listID = 'ABC-123'
		templ.name = 'Template Name'
		templ.isActive = 'maybe'
		templ.templateType = 'ASillyTemplate'
		templ.status = 'UPDATE'
		
		assert !templ.validate()
		assert "not.inList" == templ.errors["isActive"].code
		assert "not.inList" == templ.errors["templateType"].code
		assert "not.inList" == templ.errors["status"].code
		
		templ.isActive = 'true'
		templ.templateType = 'Invoice'
		templ.status = ''
		
		assert templ.validate()
	}
}
