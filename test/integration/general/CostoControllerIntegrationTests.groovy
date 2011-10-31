package general

import static org.junit.Assert.*
import grails.test.*
import grails.test.mixin.*
import org.junit.*

@TestFor(CostoController)
class CostoControllerIntegrationTests {
	
	@Test
    void listaCostos() {
		for(i in 1..20) {
            def costo = new Costo (
				descripcion: "TEST-$i"
                ,costo : new BigDecimal(100)
                ,fecha : new Date()
            ).save()
    	}
    	
    	def controller = new CostoController()
        controller.index()
        assertEquals '/costo/list', controller.response.redirectedUrl

        def model = controller.list()
        assertEquals 10, model.costoInstanceList.size()
        assert 20 <= model.costoInstanceTotal
    }
	
	@Test
    void creaCosto() {
    	def costo = new Costo (
				descripcion: "TEST1"
                ,costo : new BigDecimal(100)
                ,fecha : new Date()
            ).save()
              
        def controller = new CostoController()
        def model = controller.create()
        assert model
        assert model.costoInstance
		
		controller.params.descripcion = 'TEST1'
        controller.params.fecha = new Date()
        controller.params.costo = new BigDecimal(100)
        controller.save()
		
        assert controller.response.redirectedUrl.startsWith('/costo/show')
    }
	
	@Test
    void actualizaCosto() {
        def costo = new Costo (
				descripcion: "TEST1"
                ,costo : new BigDecimal(100)
                ,fecha : new Date()
            ).save()

        def controller = new CostoController()
        controller.params.id = costo.id
        def model = controller.show()
        assert model.costoInstance
        assertEquals 'TEST1', model.costoInstance.descripcion

        controller.params.id = costo.id
        model = controller.edit()
        assert model.costoInstance
        assertEquals 'TEST1', model.costoInstance.descripcion

        controller.params.id = costo.id
        controller.params.version = costo.version
        controller.params.descripcion = 'TEST2'
        controller.update()
        assertEquals "/costo/show/${costo.id}".toString(), controller.response.redirectedUrl
        //assert controller.response.redirectedUrl.startsWith("/costo/show/${costo.id}")

        costo.refresh()
        assertEquals 'TEST2', costo.descripcion
    }
	
	@Test
    void eliminaCosto() {
        def costo = new Costo (
				descripcion: "TEST1"
                ,costo : new BigDecimal(100)
                ,fecha : new Date()
            ).save()        
			
		def controller = new CostoController()
        controller.params.id = costo.id
        def model = controller.show()
        assert model.costoInstance
        assertEquals 'TEST1', model.costoInstance.descripcion

        controller.params.id = costo.id
        controller.delete()
        assertEquals "/costo/list", controller.response.redirectedUrl

        model = Costo.get(costo.id)
        assert !model
    }
    
}
