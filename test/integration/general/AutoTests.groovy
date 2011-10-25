package general
import login.*

import static org.junit.Assert.*
import org.junit.*

class AutoTests {

    @Test
    void listaAuto() {
        // Setup logic here
        println "Entrando a creaAuto"

//        def usuario = new Usuario(
//                username:'admin',
//                password:'admin',
//                nombre: 'Comprador',
//                apellido: 'de Autos',
//                fechaDeNacimiento: '07/12/2011',
//                telefono: '1234567890',
//                email: 'admin@auto.com'
//            ).save()
            
        for(i in 1..10){
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
        
        def controller = new AutoController()
        controller.index()
        assertEquals '/auto/list', controller.response.redirectedUrl
        
        def model = controller.list()
        assertEquals 10, model.autoInstanceList.size()
        assert 10 <= model.autoInstanceTotal
        
        println "Paso prueba"
    }
    
    void creaAuto(){
        def auto = new Auto(
                    marca: "Nissan",
                    modelo: "Sentra",
                    fechaDeModelo: "2007",
                    color: "Blanco",
                    status: "LOTE",
                    compra: new BigDecimal(100),
                    venta: new BigDecimal(100)
            ).save(flus:true)
            
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
