package general
import login.*

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import grails.plugins.springsecurity.Secured


class AutoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
    def autoService
    def springSecurityService
    
    def index() {
        redirect(action: "list", params: params)
    }

    //Lista principal de autos en venta
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def resultado = autoService.buscaAutosEnVenta(params)
        [autoInstanceList: resultado.lista, autoInstanceTotal: resultado.cantidad]
    }
    
    @Secured(['ROLE_ADMIN','ROLE_VENDEDOR'])
    def create() {
        [autoInstance: new Auto(params)]
    }

    @Secured(['ROLE_ADMIN','ROLE_VENDEDOR'])
    def save() {

        def autoInstance
        try{
        Auto.withTransaction{
         autoInstance = new Auto(params)
         
        println("imagen ----- $params.imagen")
        def archivo = request.getFile('imagen')
                if (!archivo.empty) {
                    byte[] f = archivo.bytes
                    def imagen = new Imagen(
                        nombre : archivo.originalFilename
                        , tipoContenido : archivo.contentType
                        , tamano : archivo.size
                        , archivo : f
                    )
                    if (autoInstance.imagenes) {
                        aautoInstanceuto.imagenes?.clear()
                    } else {
                        autoInstance.imagenes = []
                    }
                    autoInstance.imagenes << imagen
                    //auto.save()
           }

                
           autoInstance = autoService.crea(autoInstance)
           
           flash.message = message(code: 'default.created.message', args: [message(code: 'auto.label', default: 'Auto'), autoInstance.id])
           redirect(action: "show", id: autoInstance.id)
           
        
        }
        }catch(Exception e){
             log.error("No se pudo crear el auto ",e)
                if (autoInstance) {
                autoInstance.discard()
            }
            flash.message = message(code:"auto.noCrea")
            render(view:"create", model: [autoInstance: autoInstance])
           }
    }

    @Secured(['ROLE_ADMIN','ROLE_VENDEDOR','ROLE_COMPRADOR'])
    def show() {
        def autoInstance = Auto.get(params.id)
        if (!autoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
            return
        }

        [autoInstance: autoInstance]
    }
    
    def verMas() {
        def autoInstance = Auto.get(params.id)
        if (!autoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
            return
        }

        [autoInstance: autoInstance]
    }
    
    def finCompra() {
        def autoInstance = Auto.get(params.id)
        if (!autoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
            return
        }

        [autoInstance: autoInstance]
    }

    @Secured(['ROLE_ADMIN','ROLE_VENDEDOR'])
    def edit() {
        def autoInstance = Auto.get(params.id)
        if (!autoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
            return
        }

        [autoInstance: autoInstance]
    }

    @Secured(['ROLE_ADMIN','ROLE_VENDEDOR','ROLE_COMPRADOR'])
    def update() {
        def autoInstance = Auto.get(params.id)
        if (!autoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (autoInstance.version > version) {
                autoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'auto.label', default: 'Auto')] as Object[],
                          "Another user has updated this Auto while you were editing")
                render(view: "edit", model: [autoInstance: autoInstance])
                return
            }
        }

        autoInstance.properties = params

        if (!autoInstance.save(flush: true)) {
            render(view: "edit", model: [autoInstance: autoInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'auto.label', default: 'Auto'), autoInstance.id])
        redirect(action: "show", id: autoInstance.id)
    }

    @Secured(['ROLE_ADMIN','ROLE_VENDEDOR','ROLE_COMPRADOR'])
    def delete() {
        def autoInstance = Auto.get(params.id)
        if (!autoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
            return
        }

        try {
            autoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
    
    //Lista de Autos del usuario
    def buscaAuto = {
        def autoInstance = Auto.get(params.id)
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def resultado = autoService.listadeAutos(params)
        [autoInstanceList : resultado.listas, autoInstanceTotal: Auto.count()]
    }
    
    //Busqueda de autos para comprar
    def encuentraAuto = {
        def autoInstance = Auto.get(params.id)
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def resultado = autoService.encuntraAut(params)
        [autoInstanceList : resultado.listas, autoInstanceTotal: resultado.cantidad]
    }
    
    //Clik en comprar auto
    def compraAuto = {
        if (springSecurityService.isLoggedIn()){
            def autoOtro = Auto.get(params.id)
            autoOtro.status = "VENDIDO"
            
//            def autoNuevo = new Auto(
//                marca: autoOtro.marca,
//                modelo: autoOtro.modelo,
//                fechaDeModelo: autoOtro.fechaDeModelo,
//                color: autoOtro.color,
//                status: "LOTE",
//                compra: autoOtro.venta,
//                venta: new BigDecimal("0.00")
//            )
//            
//            def nue = autoService.crea(autoNuevo)
            
            //flash.message = message(code: "¡Ahora ya eres dueño de este Automovil! ¡Felicidades!")
            //flash.message = message(code: 'ninguno.ninguno', args: [nue])
            redirect(action: "finCompra", id: autoOtro.id)
      }
      else {
          println("NO esta logueado")
            //redirect(uri:'/usuario/verificaInicio.gsp')
            redirect(controller:'login', action: 'verficaInicio')
      }
    }
    
     
    //Imagen del auto
    def imagen = {
                try {
                    def auto = Auto.get(params.id)
                    def foto
                    for(x in auto?.imagenes) {
                        foto = x
                        break;
                    }
                    if (!foto) {
                        def directorio = servletContext.getRealPath("/images")
                        def file = new File("${directorio}/auto.jpg")
                        foto = new Imagen(
                            nombre : 'auto.jpg'
                            , tipoContenido : 'image/jpeg'
                            , tamano : file.size()
                            , archivo : file.getBytes()
                        )
                    }
                    log.debug "Mostrando imagen ${foto.nombre}"
                    log.debug "TipoContenido: ${foto.tipoContenido}"
                    response.contentType = foto.tipoContenido
                    //response.setHeader("Content-disposition", "attachment; filename=${foto?.nombre}")
                    log.debug "Tamano: ${foto.tamano}"
                    response.contentLength = foto.tamano
                    response.outputStream << foto.archivo
                    //response.outputStream.flush()
                    //return;
                } catch(Exception e) {
                    log.error("No se pudo obtener la imagen", e)
                }
            }
   
}
