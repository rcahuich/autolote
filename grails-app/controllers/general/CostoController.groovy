package general

import javax.sql.DataSource
import groovy.sql.Sql

import org.springframework.dao.DataIntegrityViolationException

class CostoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [costoInstanceList: Costo.list(params), costoInstanceTotal: Costo.count()]
    }

    def create() {
        [costoInstance: new Costo(params)]
    }

    def save() {
        def costoInstance = new Costo(params)
        if (!costoInstance.save(flush: true)) {
            render(view: "create", model: [costoInstance: costoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'costo.label', default: 'Costo'), costoInstance.id])
        redirect(controller:"auto", action: "show", id: costoInstance.auto.id)
    }

    def show() {
        def costoInstance = Costo.get(params.id)
        
        if (!costoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'costo.label', default: 'Costo'), params.id])
            redirect(action: "list")
            return
        }
        
        [costoInstance: costoInstance]
    }

    def edit() {
        def costoInstance = Costo.get(params.id)
        if (!costoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'costo.label', default: 'Costo'), params.id])
            redirect(action: "list")
            return
        }

        [costoInstance: costoInstance]
    }

    def update() {
        def costoInstance = Costo.get(params.id)
        if (!costoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'costo.label', default: 'Costo'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (costoInstance.version > version) {
                costoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'costo.label', default: 'Costo')] as Object[],
                          "Another user has updated this Costo while you were editing")
                render(view: "edit", model: [costoInstance: costoInstance])
                return
            }
        }

        costoInstance.properties = params

        if (!costoInstance.save(flush: true)) {
            render(view: "edit", model: [costoInstance: costoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'costo.label', default: 'Costo'), costoInstance.id])
        redirect(action: "show", id: costoInstance.id)
    }

    def delete() {
        def costoInstance = Costo.get(params.id)
        if (!costoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'costo.label', default: 'Costo'), params.id])
            redirect(action: "list")
            return
        }

        try {
            costoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'costo.label', default: 'Costo'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'costo.label', default: 'Costo'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
