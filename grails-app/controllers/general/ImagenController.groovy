package general

class ImagenController {

    def scaffold = Imagen

    def save = {
        def imagen
        try {
            Imagen.withTransaction {
                imagen = new Imagen(params)

                def archivo = request.getFile('imagen')
                if (!archivo.empty) {
                    imagen.nombre = archivo.originalFilename
                    imagen.tipoContenido = archivo.contentType
                    imagen.tamano = archivo.size
                    imagen.archivo = archivo.bytes
                }

                imagen.save()
                flash.message = 'La imagen ha sido creada'
                redirect(action:'show',id:imagen.id)
            }
        } catch(Exception e) {
            log.error "No se pudo crear la imagen", e
            flash.message = "No se pudo crear la imagen"
            render(view:'create',model:[imagen:imagen])
        }
    }

    def mostrar = {
        def imagen = Imagen.get(params.id)
        if (imagen) {
            response.contentType = imagen.tipoContenido
            response.contentLength = imagen.tamano
            response.outputStream << imagen.archivo
            return;
        } else {
            throw new RuntimeException('No se encontro la imagen')
        }
    }
}
