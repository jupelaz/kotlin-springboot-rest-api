package com.example.carshop

import com.example.carshop.model.Car
import com.example.carshop.model.CarRequest
import org.springframework.stereotype.Service
import java.util.*


interface CarService {
    fun saveCar(car: CarRequest): Car
    fun readCar(carId: String): Car
    fun readCars():List<Car>
    fun updateCar(carId: String, car: CarRequest): Car
    fun deleteCar(carId: String): Car
}

@Service
class CarServiceImp(val repository:CarRepository ) : CarService {
    override fun saveCar(car: CarRequest): Car = repository.save(car.toCar())
    override fun readCar(carId: String): Car = repository
        .findById(UUID.fromString(carId))
        .orElseThrow {
            AccountNotFoundException(ACCOUNT_NOT_FOUND, carId)
        }

    override fun readCars(): List<Car> = repository.findAll()
    override fun updateCar(carId: String, car: CarRequest): Car {
        if (!repository.existsById(UUID.fromString(carId))) throw AccountNotFoundException(ACCOUNT_NOT_FOUND, carId)
        val carToSave = car.toCar(UUID.fromString(carId))
        return repository.save(carToSave)
    }

    override fun deleteCar(carId: String): Car {
        val car = repository.findById(UUID.fromString(carId)).orElseThrow {
            AccountNotFoundException(ACCOUNT_NOT_FOUND, carId)
        }
        car.activeFlag = false
        return repository.save(car)
    }
}
