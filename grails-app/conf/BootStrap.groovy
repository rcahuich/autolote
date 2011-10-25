class BootStrap {
    
    def springSecurityService

    def init = { servletContext ->
        println("Inicializando aplicacion")
        println("Validando roles")
        
        def rolAdmin = login.Rol.findByAuthority('ROLE_ADMIN') ?: new login.Rol(authority: 'ROLE_ADMIN').save(failOnError: true)
        def rolComprador = login.Rol.findByAuthority('ROLE_COMPRADOR') ?: new login.Rol(authority: 'ROLE_COMPRADOR').save(failOnError: true)
        def rolUser = login.Rol.findByAuthority('ROLE_USER') ?: new login.Rol(authority: 'ROLE_USER').save(failOnError: true)
              
        log.info "Validando usuarios"
        def admin = login.UsuarioRol.findByRol(rolAdmin)
        if (!admin) {
            admin = new login.Usuario(
                username:'admin',
                password:'admin',
                nombre: 'Comprador',
                apellido: 'de Autos',
                fechaDeNacimiento: '07/12/2011',
                telefono: '1234567890',
                email: 'admin@auto.com'
            )
            admin.save(flush:true)
            login.UsuarioRol.create(admin, rolAdmin, true)
        }

        println("Aplicacion inicializada")
    }
    
    def destroy = {
    }
}
