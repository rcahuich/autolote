package general
import login.*

class AutoService {
    
    def springSecurityService

    def listadeAutos(def params){
        def autos
        if(params?.filtro){
            //autos = Auto.findAllWhere(status: params.filtro, usuario:Usuario.get(springSecurityService.getPrincipal().id))
            autos = Auto.findAllByStatus(params.filtro)
        }
        return [listas:autos]
    }
    
    def encuntraAut(def params){
        def autos
        if(springSecurityService.isLoggedIn()){
            if(params?.filtro){
                def usuario = Usuario.get(springSecurityService.getPrincipal().id)
                autos = Auto.executeQuery("select auto from Auto auto where auto.status = 'EN VENTA' and auto.marca = :filtro and auto.usuario != :currentUser", [filtro:params.filtro, currentUser:usuario])
            } 
        }else{
                autos = Auto.executeQuery("select auto from Auto auto where auto.status = 'EN VENTA' and auto.marca = :filtro", [filtro:params.filtro])
        }
        println(" =============  ${autos} ======")
        def autoSize = autos.size()
        return [listas:autos, cantidad:autoSize]
    }
    
     Auto crea(Auto auto) {
        println("Creando auto $auto")
        def usuario = Usuario.get(springSecurityService.getPrincipal().id)

        auto.usuario = usuario
        auto = auto.save()
        
        return auto
    }
    
    Map buscaAutosEnVenta(params){
        
        if(springSecurityService.isLoggedIn()){
            println("========== IF ==========")
            def usuario = Usuario.get(springSecurityService.getPrincipal().id)
            def autosV = Auto.executeQuery("select auto from Auto auto where auto.status = 'EN VENTA' and auto.usuario != :vendedor", [vendedor: usuario])
            println("========== DATOS $autosV ==========")
            def cantidad = autosV.size()
            return [lista:autosV, cantidad:cantidad]        
        }else{
            println("========== ELSE ==========")
            def autosV = Auto.executeQuery("select auto from Auto auto where auto.status = 'EN VENTA'")
            println("========== DATOS $autosV ==========")
            def cantidad = autosV.size()
            return [lista:autosV, cantidad:cantidad]
        }
    }
}
