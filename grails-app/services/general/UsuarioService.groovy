package general
import login.*
class UsuarioService {

    Usuario crea(usuario, roles) {
        //def creador = Usuario.get(springSecurityService.principal.id)
        //usuario.asociacion = creador.asociacion
        if (usuario.save(flush: true)) {
        for(rol in roles) {
            UsuarioRol.create(usuario, rol, false)
            }
        }
        return usuario
    }
}
