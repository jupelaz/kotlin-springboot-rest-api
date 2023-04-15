package com.example.carshop

import com.example.carshop.model.Car
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull
import java.util.*


@DataJpaTest
class CarRepositoryTest {

    @Autowired
    lateinit var repository: CarRepository

    val car = Car(UUID.randomUUID(),"Mustang","Ford",1983,80000.00,true)

    @Test
    fun findEmpty() {
        val cars = repository.findAll()
        assertEquals(
            cars,
            Collections.EMPTY_LIST,
            "Should be no cars if repository is empty"
        )
    }

    @Test
    fun findAny() {
        repository.save(car)
        assertNotEquals(
            repository.findAll(), Collections.EMPTY_LIST,
            "should be an Car if an Account is saved"
        )
    }

    @Test
    fun findOne() {
        val returnedCar: Car = repository.save(car)
        assertNotNull(
            repository.findById(returnedCar.id).orElse(null),
            "There should be a Car with this id"
        )
        var randomId = UUID.randomUUID()
        while (returnedCar.id ==randomId) randomId = UUID.randomUUID()
        assertNull(
            repository.findById(UUID.randomUUID()).orElse(null),
            "There should be no Car with a random id"
        )
    }

    @Test
    fun save() {
        val savedCar = repository.save(car)
        assertAll("All car properties should be equal in car and in the car saved",
            { assertEquals(savedCar.model, car.model) },
            { assertEquals(savedCar.brand, car.brand) },
            { assertEquals(savedCar.productionYear, car.productionYear) },
            { assertEquals(savedCar.price, car.price) }
        )
    }

    @Test
    fun update() {
        val carSaved = repository.save(car)
        val updatedCarSaved = carSaved.copy(price = 85000.00)
        repository.save(updatedCarSaved)
        val foundCar = repository.findByIdOrNull(carSaved.id)
        assertEquals(foundCar?.price, 85000.00, "Price should be the updated one")

    }

    @Test
    fun delete() {
        val carSaved = repository.save(car)
        assertNotNull(repository.findByIdOrNull(carSaved.id), "new account should exist")
        repository.delete(carSaved)
        assertNull(repository.findByIdOrNull(carSaved.id), "Account should be deleted")
    }
}
