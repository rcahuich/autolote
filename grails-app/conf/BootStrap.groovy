class BootStrap {
    
    def springSecurityService

    def init = { servletContext ->
        log.info("Inicializando aplicacion")
        log.info "Validando roles"
        def rolAdmin = login.Rol.findByAuthority('ROLE_ADMIN')
        if (login.Rol.count() != 3) {
            if (!rolAdmin) {
                rolAdmin = new login.Rol(authority: 'ROLE_ADMIN').save(flush:true)
            }

            def rolComprador = login.Rol.findByAuthority('ROLE_COMPRADOR')
            if (!rolComprador) {
                rolComprador = new login.Rol(authority: 'ROLE_COMPRADOR').save(flush:true)
            }
            
            def rolUser = login.Rol.findByAuthority('ROLE_USER')
            if (!rolUser) {
                rolUser = new login.Rol(authority: 'ROLE_USER').save(flush:true)
            }
        }
        
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

        log.info("Aplicacion inicializada")
    }
    
    def destroy = {
    }
}
