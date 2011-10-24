package general

class AutoService {

    def listadeAutos(def params){
        def autos
        if(params?.filtro){
            autos = Auto.findAllByStatus(params.filtro)
        }
        return [listas:autos]
    }
    
    def encuntraAut(def params){
        def autos
        if(params?.filtro){
            autos = Auto.findAllByMarca(params.filtro)
        }
        return [listas:autos]
    }
}
