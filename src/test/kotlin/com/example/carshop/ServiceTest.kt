package com.example.carshop
import com.example.carshop.model.Car
import com.example.carshop.model.CarRequest
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*
class CarServiceImplTest {
    private val repository: CarRepository = mockk()
    private val service:CarServiceImp = CarServiceImp(repository)
    @AfterEach
    fun after() {
        confirmVerified(repository)
        clearAllMocks()
    }
    @Test
    fun saveCar() {
        val car:Car = mockk()
        val carRequest:CarRequest = mockk()
        every { repository.save(car) } answers { args[0] as Car }
        every { carRequest.toCar() } returns car
        val response = service.saveCar(carRequest)
        assertEquals(car, response)
        verify(exactly = 1) { repository.save(car) }
    }

    @Test
    fun readCar() {
        val car: Car = mockk()
        val id = UUID.randomUUID()
        every { repository.findById(id) } returns Optional.of(car)
        val anotherId = UUID.randomUUID()
        val response = service.readCar(id.toString())
        assertEquals(car, response)
        verify { repository.findById(id) }

        every { repository.findById(anotherId) } returns Optional.empty()
        val thrown = assertThrows(
            AccountNotFoundException::class.java
        ) { service.readCar(anotherId.toString()) }
        assertEquals(thrown.message, String.format(ACCOUNT_NOT_FOUND, anotherId))
        verify { repository.findById(anotherId) }
    }
    @Test
    fun readCars() {
        val car:Car = mockk()
        every { repository.findAll() } returns(listOf(car))
        val response = service.readCars()
        verify { repository.findAll() }
        assertEquals(response, listOf(car))
    }
    @Test
    fun updateCar() {
        val id = UUID.randomUUID()
        val car:Car = mockk()
        val carRequest:CarRequest = mockk()
        every { car.id } returns id
        every { repository.existsById(id) } returns true
        every { carRequest.toCar(id) } returns car
        every { repository.save(car) } returns car

        val response = service.updateCar(id.toString(), carRequest)
        assertEquals(car, response)
        verify { repository.existsById(id) }
        verify { repository.save(car) }
    }
    @Test
    fun deleteCar() {
        val id = UUID.randomUUID()
        val car:Car = mockk(relaxed = true)
        every {repository.findById(id) } returns Optional.of(car)
        every {repository.save(car) } answers { args[0] as Car}
        car.activeFlag = false
        val response = service.deleteCar(id.toString())
        assertEquals(car.id, response.id)
        assertFalse(response.activeFlag)
        verify { repository.findById(id) }
        verify { repository.save(car) }
    }
}