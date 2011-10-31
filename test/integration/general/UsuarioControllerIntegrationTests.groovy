package general
import login.*

import static org.junit.Assert.*
import org.junit.*
import grails.test.*
import grails.test.mixin.*

@TestFor(UsuarioController)
class UsuarioControllerIntegrationTests {

	def springSecurityService
	
    @Test
    void listaUsuarios() {
        // Setup logic here
		authenticateAdmin()
		
		for(i in 1..20) {
            def usuario = new Usuario (
                username: "admin$i"
                ,password: "admin$i"
                ,enabled: true
                ,accountExpired: false
                ,accountLocked: false
                ,passwordExpired: false
                ,nombre : "TEST$i"
                ,apellido : "TEST$i"
                ,fechaDeNacimiento: new Date()
                ,email : "test@test.com"                
                ,telefono : "1234567890"
            ).save()
        }
		
		def controller = new UsuarioController()
        controller.index()
        assertEquals '/usuario/list', controller.response.redirectedUrl
        
        def model = controller.list()
        assertEquals 10, model.usuarioInstanceList.size()
        assert 20 <= model.usuarioInstanceTotal
    }
	
	@Test
    void creaUsuario() {
        def controller = new UsuarioController()
        def model = controller.create()
        assert model
        assert model.usuarioInstance

        controller.params.username = 'admin1'
        controller.params.password = 'admin1'
        controller.params.enabled = true
        controller.params.accountExpired = false
        controller.params.accountLocked = false
        controller.params.passwordExpired = false
        controller.params.nombre = 'TEST1'
        controller.params.apellido = 'TEST1'
        controller.params.fechaDeNacimiento = new Date()
        controller.params.email = 'test@test.com'
        controller.params.telefono = '1234567890'
        controller.save()
		
        assert controller.response.redirectedUrl.startsWith('/usuario/show')
    }
	
	@Test
    void actualizaUsuario() {
        def usuario = new Usuario (
                username: "admin1"
                ,password: "admin1"
                ,enabled: true
                ,accountExpired: false
                ,accountLocked: false
                ,passwordExpired: false
                ,nombre : "TEST1"
                ,apellido : "TEST1"
                ,fechaDeNacimiento: new Date()
                ,email : "test@test.com"                
                ,telefono : "1234567890"
            ).save()

        def controller = new UsuarioController()
        controller.params.id = usuario.id
        def model = controller.show()
        assert model.usuarioInstance
        assertEquals 'TEST1', model.usuarioInstance.nombre

        controller.params.id = usuario.id
        model = controller.edit()
        assert model.usuarioInstance
        assertEquals 'TEST1', model.usuarioInstance.nombre

        controller.params.id = usuario.id
        controller.params.version = usuario.version
        controller.params.nombre = 'TEST2'
        controller.update()
        assertEquals "/usuario/show/${usuario.id}".toString(), controller.response.redirectedUrl
        //assert controller.response.redirectedUrl.startsWith("/usuario/show/${usuario.id}")

        usuario.refresh()
        assertEquals 'TEST2', usuario.nombre
    }
	
	@Test
    void eliminaUsuario() {
        def usuario = new Usuario (
                username: "admin1"
                ,password: "admin1"
                ,enabled: true
                ,accountExpired: false
                ,accountLocked: false
                ,passwordExpired: false
                ,nombre : "TEST1"
                ,apellido : "TEST1"
                ,fechaDeNacimiento: new Date()
                ,email : "test@test.com"                
                ,telefono : "1234567890"
            ).save()

        def controller = new UsuarioController()
        controller.params.id = usuario.id
        def model = controller.show()
        assert model.usuarioInstance
        assertEquals 'TEST1', model.usuarioInstance.nombre

        controller.params.id = usuario.id
        controller.delete()
        assertEquals "/usuario/list", controller.response.redirectedUrl

        model = Usuario.get(usuario.id)
        assert !model
    }

}
