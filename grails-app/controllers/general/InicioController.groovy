package general

import grails.plugins.springsecurity.Secured

class InicioController {
    
    def index = {
        log.debug "Mostrando pagina de inicio"
    }
}