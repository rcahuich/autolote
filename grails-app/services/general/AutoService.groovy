package general

class AutoService {

    def listadeAutos(def params){
        def autos
        if(params?.filtro){
            autos = Auto.findAllByStatus(params.filtro)
        }
        return [listas:autos]
    }
}
