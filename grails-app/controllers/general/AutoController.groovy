package general

import org.springframework.dao.DataIntegrityViolationException

class AutoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def autoService
    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [autoInstanceList: Auto.list(params), autoInstanceTotal: Auto.count()]
    }

    def create() {
        [autoInstance: new Auto(params)]
    }

    def save() {
        def autoInstance = new Auto(params)
        if (!autoInstance.save(flush: true)) {
            render(view: "create", model: [autoInstance: autoInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'auto.label', default: 'Auto'), autoInstance.id])
        redirect(action: "show", id: autoInstance.id)
    }

    def show() {
        def autoInstance = Auto.get(params.id)
        if (!autoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
            return
        }

        [autoInstance: autoInstance]
    }

    def edit() {
        def autoInstance = Auto.get(params.id)
        if (!autoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
            return
        }

        [autoInstance: autoInstance]
    }

    def update() {
        def autoInstance = Auto.get(params.id)
        if (!autoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (autoInstance.version > version) {
                autoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'auto.label', default: 'Auto')] as Object[],
                          "Another user has updated this Auto while you were editing")
                render(view: "edit", model: [autoInstance: autoInstance])
                return
            }
        }

        autoInstance.properties = params

        if (!autoInstance.save(flush: true)) {
            render(view: "edit", model: [autoInstance: autoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'auto.label', default: 'Auto'), autoInstance.id])
        redirect(action: "show", id: autoInstance.id)
    }

    def delete() {
        def autoInstance = Auto.get(params.id)
        if (!autoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
            return
        }

        try {
            autoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
    
    def buscaAuto = {
        log.debug("##################### inicio")
        def autoInstance = Auto.get(params.id)
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        log.debug("##################### ${params.filtro}")
        def resultado = autoService.listadeAutos(params)
        
        [autoInstanceList : resultado.listas, autoInstanceTotal: Auto.count()]
    }
}
