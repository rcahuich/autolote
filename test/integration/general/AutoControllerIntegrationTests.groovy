package general
import general.*
import login.*

import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(AutoController)
class AutoControllerIntegrationTests {

    @Test
    void pruebasAuto() {
        
        def usuario = new Usuario(
                username:'otro',
                password:'otro',
                nombre: 'Comprador',
                apellido: 'de Autos',
                fechaDeNacimiento: new Date(),
                telefono: '1234567890',
                email: 'admin@auto.com'
            ).save()
            
      
        for(i in 1..20) {
            def auto = new Auto(
                marca: "nissan"
                ,modelo: "TEST$i"
                ,color: "TEST$i"
                ,fechaDeModelo: "2010"
                ,compra: new BigDecimal(100)
                ,venta: new BigDecimal(100)
                ,status: "EN VENTA"
                ,usuario:usuario
            ).save()


        }

            def lista = Auto.list()
            assert 20 == lista.size()

            def auto = Auto.findByModelo("TEST1")
            assert auto
            assert "TEST1" == auto.color

            auto.modelo = 'prueba'
            auto.save()
            def id = auto.id
            def x = Auto.get(id)
            assert x
            assert 'prueba' == x.modelo

            x.delete()

            lista = Auto.list()
            assert 19 == lista.size()

            def y = Auto.get(id)
            assert !y
        
        println "Paso prueba"
    }
    
}