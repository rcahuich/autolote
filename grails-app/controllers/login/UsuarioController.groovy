package login
import login.*

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils


class UsuarioController {
    
    def usuarioService
    def springSecurityService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    
    def index() {
        redirect(action: "list", params: params)
    }

    @Secured(['ROLE_ADMIN'])
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [usuarioInstanceList: Usuario.list(params), usuarioInstanceTotal: Usuario.count()]
    }

    //@Secured(['ROLE_ADMIN','ROLE_VENDEDOR'])
    def create() {
        def usuario = new Usuario()
        usuario.properties = params
        def roles = obtieneListaDeRoles(null)
        [usuarioInstance: usuario, roles: roles]
    }
    
    def save() {
        
        def usuario
        try {
            Usuario.withTransaction {
                usuario = new Usuario(params)
                
                    def roles = asignaRoles(params)
                    def roles2 = [] as Set
                    
                    if(SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN')){
                        println("==== roladmin")
                        println("==== aut $roles")
                        usuario = usuarioService.crea(usuario, roles)
                        flash.message = message(code: "Has registrado correctamente al usuario")
                        redirect(action:'show', id: usuario.id)
                    }else
                    if(SpringSecurityUtils.ifAnyGranted('ROLE_VENDEDOR')){
                        roles2 << Rol.findByAuthority('ROLE_COMPRADOR')
                        println("==== rolvendedor")
                        println("==== aut $roles2")
                        usuario = usuarioService.crea(usuario, roles2)
                        flash.message = message(code: "Has registrado correctamente al usuario")
                        redirect(action:'show', id: usuario.id)
                    }else{
                        roles2 << Rol.findByAuthority('ROLE_COMPRADOR')
                        println("==== otro rol")
                        println("==== aut $roles2")
                        usuario = usuarioService.crea(usuario, roles2)
                        flash.message = message(code: "Has sido registrado!, por favor inicia sesión")
                        redirect(controller:'login', action:'auth')
                    }
                
            }
        } catch(Exception e) {
            log.error("No se pudo crear el usuario",e)
            if (usuario) {
                usuario.discard()
            }
            flash.message = message(code:"usuario.noCrea")
            render(view:"create", model: [usuario: usuario])
        }
    

    }

    @Secured(['ROLE_ADMIN','ROLE_VENDEDOR'])
    def show() {
        def usuarioInstance = Usuario.get(params.id)
        if (!usuarioInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "list")
            return
        }

        [usuarioInstance: usuarioInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def edit() {
        def usuarioInstance = Usuario.get(params.id)
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "list")
            return
        }

        [usuarioInstance: usuarioInstance]
    }

    @Secured(['ROLE_ADMIN'])
    def update() {
        def usuarioInstance = Usuario.get(params.id)
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (usuarioInstance.version > version) {
                usuarioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'usuario.label', default: 'Usuario')] as Object[],
                          "Another user has updated this Usuario while you were editing")
                render(view: "edit", model: [usuarioInstance: usuarioInstance])
                return
            }
        }

        usuarioInstance.properties = params

        if (!usuarioInstance.save(flush: true)) {
            render(view: "edit", model: [usuarioInstance: usuarioInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuarioInstance.id])
        redirect(action: "show", id: usuarioInstance.id)
    }

    @Secured(['ROLE_ADMIN'])
    def delete() {
        def usuarioInstance = Usuario.get(params.id)
        if (!usuarioInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "list")
            return
        }

        try {
            usuarioInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
    
    def asignaRoles = { params ->
        def roles = [] as Set
        if (params.ROLE_ADMIN) {
            roles << Rol.findByAuthority('ROLE_ADMIN')
        } else if (params.ROLE_VENDEDOR) {
            roles << Rol.findByAuthority('ROLE_VENDEDOR')
        } else if (params.ROLE_COMPRADOR) {
            roles << Rol.findByAuthority('ROLE_COMPRADOR')
        } else {
            roles << Rol.findByAuthority('ROLE_USER')
        }
        return roles
    }
    
    def obtieneListaDeRoles = { usuario ->
        log.debug "Obteniendo lista de roles"
        def roles = Rol.list()

        def rolesFiltrados = [] as Set
        if (SpringSecurityUtils.ifAnyGranted('ROLE_ADMIN')) {
            log.debug "Roles para ADMIN"
            rolesFiltrados = roles
        } else if(SpringSecurityUtils.ifAnyGranted('ROLE_VENDEDOR')) {
            log.debug "Roles para VENDEDOR"
            for(rol in roles) {
                if (!rol.authority.equals('ROLE_ADMIN') && !rol.authority.equals('ROLE_VENDEDOR')) {
                    rolesFiltrados << rol
                }
            }
        } else if(SpringSecurityUtils.ifAnyGranted('ROLE_COMPRADOR')) {
            log.debug "Roles para COMPRADOR"
            for(rol in roles) {
                if (rol.authority.equals('ROLE_USER')) {
                    rolesFiltrados << rol
                }
            }
        }
        roles = rolesFiltrados
        roles.sort { r1, r2 ->
            r1.authority <=> r2.authority
        }
        Set userRoleNames = []
        for (role in usuario?.authorities) {
            userRoleNames << role.authority
        }
        LinkedHashMap<Rol, Boolean> roleMap = [:]
        for (role in roles) {
            roleMap[(role)] = userRoleNames.contains(role.authority)
        }
        return roleMap
    }
    
    
}
