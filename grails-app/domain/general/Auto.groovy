package general
import login.*

class Auto {
    
    String marca
    String modelo
    String fechaDeModelo
    String color
    String status
    BigDecimal compra = new BigDecimal("0.00")
    BigDecimal venta = new BigDecimal("0.00")
    Set costos
    Set imagenes
    Usuario usuario
    
    static belongsTo = [usuario: Usuario]
    
    static hasMany = [costos : Costo, imagenes: Imagen]

    static constraints = {
        marca (inList: ["Acura", "Aston Martin", "Audi", "Bentley", "BMW", "Bugatti", "Buick", "Cadillac", "Chevrolet", "Chrysler", "Dodge", "Ferrari", "FIAT", "Fisker", "Ford", "GMC", "Honda", "Hyundai", "Infiniti", "Jaguar", "Jeep", "KIA", "Lamborghini", "Land Rover", "Lexus", "Lincoln", "Lotus", "Maserati", "Maybach", "Mazda", "McLaren", "Mercedez Benz", "Mercury", "MINI", "Mitsubishi", "Nissan", "Porsche", "Rolls-Royce", "Saab", "Scion", "Smart", "Spyker", "Subaru", "Suzuki", "Tesla", "Toyota", "Volkswagen", "Volvo"])
        status (inList: ["EN VENTA","VENDIDO", "LOTE", "TALLER"])
        
    }
    
    static mapping = {
        table 'autos'
        imagenes cascade:'all-delete-orphan'
    }

    String toString() {
        return "$marca | $modelo"
    }
}
