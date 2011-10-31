package general
import general.*
import login.*

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(AutoController)
class AutoControllerIntegrationTests {

    @Test
    void listaAuto() {
        // Setup logic here
        println "Entrando a Lista de Autos en Venta"

        def usuario = new Usuario(
                username:'otro',
                password:'otro',
                nombre: 'Comprador',
                apellido: 'de Autos',
                fechaDeNacimiento: new Date(),
                telefono: '1234567890',
                email: 'admin@auto.com'
            ).save()
            
        assertNotNull usuario
        assertNotNull usuario.id
            
        for(i in 1..20){
            def auto = new Auto(
                    marca: "Nissan",
                    modelo: "Sentra",
                    fechaDeModelo: "2007",
                    color: "Blanco",
                    status: "LOTE",
                    compra: new BigDecimal(100),
                    venta: new BigDecimal(100),
                    usuario: usuario,
                ).save(flus:true)
                
            assertNotNull auto
            assertNotNull auto.id
        }
        
//        def controller = new AutoController()
//        controller.index()
//        assertEquals '/auto/list', controller.response.redirectedUrl
//        
//        def model = controller.list()
//        assertEquals 10, model.autoInstanceList.size()
//        assert 20 <= model.autoInstanceTotal

            def lista = Auto.list()
            assertEquals 20, lista.size()
        
        println "Paso prueba"
    }
    
    @Test
    void creaAuto() {
        println "Entrando a creaAuto"
        def usuario = new Usuario(
                username:'otro',
                password:'otro',
                nombre: 'Comprador',
                apellido: 'de Autos',
                fechaDeNacimiento: new Date(),
                telefono: '1234567890',
                email: 'admin@auto.com'
            ).save()
            
        //assertNotNull usuario
        //assertNotNull usuario.id
        
        def imagen2 = new Imagen(
            nombre: 'Imagen',
            tipoContenido: 'jpg',
            tamano: 12345,
            archivo:10000000
        ).save()
        
        println "--------- $imagen2"
        def auto = new Auto(
                    marca: "Nissan",
                    modelo: "Sentra",
                    fechaDeModelo: "2007",
                    color: "Blanco",
                    status: "LOTE",
                    compra: new BigDecimal(100),
                    venta: new BigDecimal(100),
                    usuario: usuario,
                    imagen: imagen2
            ).save()
        
        println "--------- $auto"
        
        def controller = new AutoController()
        def model = controller.create()
        assert model
        assert model.autoInstance
        
        controller.params.marca = 'Nissan'
        controller.params.status = 'LOTE'
        controller.save()
        
        assert controller.response.redirectedUrl.startsWith('/auto/show')
        println "Paso prueba"
    }
    
    
    
    

}
