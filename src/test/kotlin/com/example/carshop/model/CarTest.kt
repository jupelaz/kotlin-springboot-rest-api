package com.example.carshop.model

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import java.util.*

class CarTest {

    private val id: UUID = UUID.randomUUID()
    private var car = Car(id,"Mustang","Ford",1983,80000.00,true)

    @Test
    fun getId() {
        assertEquals(id,car.id,"Id should be the one defined in the constructor")
    }

    @Test
    fun getModel() {
        assertEquals("Mustang",car.model, "model should be the one defined in the constructor" )
    }

    @Test
    fun getBrand() {
        assertEquals("Ford",car.brand,"brand should be the one defined in the constructor")
    }

    @Test
    fun getProductionYear() {
        assertEquals(1983,car.productionYear, "production year should be the one defined in the constructor")
    }

    @Test
    fun getPrice() {
        assertEquals(80000.00,car.price,"price should be the one defined in the constructor")
    }

    @Test
    fun copy() {
        val secondId = UUID.randomUUID()
        val b = car.copy(id = secondId)
        assertAll("All values should remain the same except the id",
            { assertEquals(b.id, secondId) },
            { assertEquals(b.model, car.model) },
            { assertEquals(b.brand, car.brand) },
            { assertEquals(b.productionYear, car.productionYear) },
            { assertEquals(b.price, car.price) }
        )
    }

    @Test
    fun testToString() {
        assertEquals(
            "Car(id=$id, model=Mustang, brand=Ford, productionYear=1983, price=80000.0, activeFlag=true)",
            car.toString(),
            "ToString function should return all values formatted"
        )
    }

    @Test
    fun testHashCode() {
        val b = car.copy()
        assertEquals(b.hashCode(), car.hashCode(), "tohashcode should return the same value with equal objects")
    }

    @Test
    fun testEquals() {
        val b = car.copy()
        assertTrue(b == car,"Two equal objects should return true when compared")
    }

}