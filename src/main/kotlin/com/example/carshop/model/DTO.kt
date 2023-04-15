package com.example.carshop.model
import java.util.*
data class CarRequest(
    val model:String,
    val brand:String,
    val productionYear:Int,
    val price:Double
) {
    fun toCar() = Car(UUID.randomUUID(),model,brand,productionYear,price,true)
    fun toCar(id:UUID) = Car(id,model,brand,productionYear,price,true)
}
data class CarResponse(
    val id:String,
    val model:String,
    val brand:String,
    val productionYear:Int,
    val price:Double
)
fun Car.toCarResponse():CarResponse = CarResponse(id.toString(),model,brand,productionYear,price)

class CarErrorMessage (val message: String)