package com.example.carshop

import com.example.carshop.model.CarRequest
import com.example.carshop.model.CarResponse
import com.example.carshop.model.toCarResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/car")
class CarController(val service: CarService) {
    @PostMapping
    fun createCar(@RequestBody carRequest: @Valid CarRequest): ResponseEntity<CarResponse> {
        val car = service.saveCar(carRequest)
        return ResponseEntity.ok(car.toCarResponse())
    }

    @PutMapping("/{id}/status")
    @Throws(ArrayIndexOutOfBoundsException::class)
    fun updateCar(
        @PathVariable id: UUID,
        @RequestBody carRequest: @Valid CarRequest
    ): ResponseEntity<Void>  {
        service.updateCar(id.toString(), carRequest)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @GetMapping("/{id}/account")
    fun getAccount(@PathVariable id: UUID): ResponseEntity<CarResponse> {
        val car = service.readCar(id.toString())
        return ResponseEntity.status(HttpStatus.OK).body(car.toCarResponse())
    }

}