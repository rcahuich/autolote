package general
import login.*

import static org.junit.Assert.*
import org.junit.*

class AutoTests {

    @Test
    void listaAuto() {
        // Setup logic here
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
            
        assertNotNull usuario
        assertNotNull usuario.id
            
        for(i in 1..20){
            def cont = i
            cont++
            println("-- $i --")
            def auto = new Auto(
                    marca: "Nissan",
                    modelo: "Sentra",
                    fechaDeModelo: "2007",
                    color: "Blanco",
                    status: "LOTE",
                    compra: new BigDecimal(100),
                    venta: new BigDecimal(100),
                    usuario: usuario
                ).save(flus:true)
                
            assertNotNull auto
            assertNotNull auto.id
        }
        
//        def controller = new AutoController()
//        controller.index()
//        assertEquals '/auto/list', controller.response.redirectedUrl
        
//        def model = controller.list()
//        assertEquals 10, model.autoInstanceList.size()
//        assert 20 <= model.autoInstanceTotal

            def lista = Auto.list()
            println("-- ${lista.size()}")
            assertEquals 22, lista.size()
        
        println "Paso prueba"
    }
    
    void creaAuto(){
        
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
        
        def auto = new Auto(
                    marca: "Nissan",
                    modelo: "Sentra",
                    fechaDeModelo: "2007",
                    color: "Blanco",
                    status: "LOTE",
                    compra: new BigDecimal(100),
                    venta: new BigDecimal(100)
            ).save()
            
        def controller = new AutoController()
        def model = controller.create()
        assert model
        assert model.auto
        
        controller.params.marca = 'Nissan'
        controller.params.modelo = 'Sentra'
        controller.params.fechaDeModelo = '2007'
        controller.params.color = 'Blanco'
        controller.params.status = 'LOTE'
        controller.params.compra = new BigDecimal(100)
        controller.params.venta = new BigDecimal(100)
        controller.save()
        
    }
    
    
    
    

}
