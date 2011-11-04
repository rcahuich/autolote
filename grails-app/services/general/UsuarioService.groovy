package general
import login.*
class UsuarioService {

    Usuario crea(usuario, roles) {
        
        try {
            if (usuario.save(flush: true)) {
                for(rol in roles) {
                    UsuarioRol.create(usuario, rol, false)
                }
                return usuario
            }
            
        } catch(Exception e) {
            println("erooor ${usuario.errors}")
            if (usuario) {
                usuario.discard()
            }
        }
    }
}
