package general

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

    
    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [autoInstanceList: Auto.list(params), autoInstanceTotal: Auto.count()]
    }
    
    @Secured(['ROLE_ADMIN','ROLE_COMPRADOR'])
    def create() {
        [autoInstance: new Auto(params)]
    }

    @Secured(['ROLE_ADMIN','ROLE_COMPRADOR'])
    def save() {
        def autoInstance = new Auto(params)
        
//        println("imagen ----- $params.imagen")
//        def archivo = request.getFile('imagen')
//                if (!archivo.empty) {
//                    byte[] f = archivo.bytes
//                    def imagen = new Imagen(
//                        nombre : archivo.originalFilename
//                        , tipoContenido : archivo.contentType
//                        , tamano : archivo.size
//                        , archivo : f
//                    )
//                    if (jugador.imagenes) {
//                        jugador.imagenes?.clear()
//                    } else {
//                        jugador.imagenes = []
//                    }
//                    jugador.imagenes << imagen
//                    jugador.save()
//           }

        
        
        
        
        
           
        if (!autoInstance.save(flush: true)) {
            render(view: "create", model: [autoInstance: autoInstance])
            return
        }
            
            println("----------- autoId $autoInstance.id")
            println("----------- usuarioId $springSecurityService.principal.id")
            
            def ua = new UsuarioAuto(
                    auto: autoInstance.id,
                    usuario: springSecurityService.principal.id
            ).save(flush: true)
            
	flash.message = message(code: 'default.created.message', args: [message(code: 'auto.label', default: 'Auto'), autoInstance.id])
        redirect(action: "show", id: autoInstance.id)
    }

    @Secured(['ROLE_ADMIN','ROLE_COMPRADOR','ROLE_USER'])
    def show() {
        def autoInstance = Auto.get(params.id)
        if (!autoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
            return
        }

        [autoInstance: autoInstance]
    }

    @Secured(['ROLE_ADMIN','ROLE_COMPRADOR'])
    def edit() {
        def autoInstance = Auto.get(params.id)
        if (!autoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'auto.label', default: 'Auto'), params.id])
            redirect(action: "list")
            return
        }

        [autoInstance: autoInstance]
    }

    @Secured(['ROLE_ADMIN','ROLE_COMPRADOR'])
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

    @Secured(['ROLE_ADMIN','ROLE_COMPRADOR'])
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
    
    @Secured(['ROLE_ADMIN','ROLE_COMPRADOR'])
    def buscaAuto = {
        println("filtrooo ##### %${params.filtro}%")
        def autoInstance = Auto.get(params.id)
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def resultado = autoService.listadeAutos(params)
        println("resultado ##### %${resultado}%")
        [autoInstanceList : resultado.listas, autoInstanceTotal: Auto.count()]
    }
    
   
    def encuentraAuto = {
        println("filtrooo ##### %${params.filtro}%")
        def autoInstance = Auto.get(params.id)
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def resultado = autoService.encuntraAut(params)
        println("resultado ##### %${resultado}%")
        [autoInstanceList : resultado.listas, autoInstanceTotal: Auto.count()]
    }
    
   
    def compraAuto = {
        if (springSecurityService.isLoggedIn()) {
            println("SI esta logueado")
            
            def autoOtro = Auto.get(params.id)
            autoOtro.status = "VENDIDO"
            
            def autoNuevo = new Auto(
                marca: autoOtro.marca,
                modelo: autoOtro.modelo,
                fechaDeModelo: autoOtro.fechaDeModelo,
                color: autoOtro.color,
                status: "LOTE",
                compra: autoOtro.venta,
                venta: new BigDecimal("0.00")
            )
            
            autoNuevo.save(flush:true)
            autoOtro.save(flush:true)
            
            flash.message = message(code: 'ninguno.ninguno', args: [autoNuevo])
            redirect(action: "show", id: autoNuevo.id)
            
            
      }
      else {
          println("NO esta logueado")
            //redirect(uri:'/usuario/verificaInicio.gsp')
            redirect(controller:"usuario", action: "verficaInicio")
      }
    }
    
     
    
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
