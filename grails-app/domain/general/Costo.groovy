package general

class Costo {
    
    String descripcion
    BigDecimal costo = new BigDecimal("0.00")
    Date fecha
    
    static belongsTo = [auto:Auto]
    
    static constraints = {
        descripcion (blank:false)
        costo (blank:false)
    }
    
    static mapping = {
        table 'costos'
    }
    
    String toString() {
        return "$costo de $descripcion"
    }
}
