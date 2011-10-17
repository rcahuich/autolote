package login
import general.*

class Usuario {

	transient springSecurityService

	String username
	String password
	String nombre
	String apellido
	Date fechaDeNacimiento
	String email
	String telefono 
        boolean enabled = true
	boolean accountExpired = false
	boolean accountLocked = false
	boolean passwordExpired = false
        Set autos

        static hasMany = [autos:Auto]
        
	static constraints = {
		username blank: false, unique: true
		password blank: false
                nombre nullable: false
                apellido nullable: false
                telefono matches:"[0-9]{10}"
                email mail:true
	}

	static mapping = {
                table 'usuarios'
		password column: '`password`'
	}

	Set<Rol> getAuthorities() {
		UsuarioRol.findAllByUsuario(this).collect { it.rol } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
        
        String toString() {
            return nombre
        }
}
