package com.example.carshop
import com.example.carshop.model.Car
import com.example.carshop.model.CarRequest
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import java.util.*

class CarControllerTest {
    private val service:CarService = mockk()
    private val controller = CarController(service)

    @AfterEach
    fun afterAll() {
        confirmVerified(service)
        clearAllMocks()
    }
    @Test
    fun createCar() {
        val carRequest: CarRequest = mockk()
        val car: Car = mockk(relaxed = true)
        every { service.saveCar(carRequest)} returns car
        controller.createCar(carRequest)
        verify { service.saveCar(carRequest) }
    }

    @Test
    fun updateAccount() {
        val carRequest: CarRequest = mockk()
        val car:Car = mockk()
        val id = UUID.randomUUID()
        every { service.updateCar(id.toString(), carRequest)} returns car
        controller.updateCar(id,carRequest)
        verify { service.updateCar(id.toString(),carRequest) }
    }

    @Test
    fun getAccount() {
        val car: Car = mockk(relaxed = true)
        val id = UUID.randomUUID()
        every { car.id } returns id
        every { service.readCar(id.toString()) } returns car
        controller.getAccount(id)
        verify { service.readCar(id.toString()) }
    }

}