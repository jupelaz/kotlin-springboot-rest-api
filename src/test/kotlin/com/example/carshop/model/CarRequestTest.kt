package com.example.carshop.model
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
class CarRequestTest {
    private var carRequest = CarRequest("Mustang","Ford",1983,80000.00)
    @Test
    fun getModel() {
        assertEquals("Mustang",carRequest.model, "model should be the one defined in the constructor" )
    }

    @Test
    fun getBrand() {
        assertEquals("Ford",carRequest.brand,"brand should be the one defined in the constructor")
    }

    @Test
    fun getProductionYear() {
        assertEquals(1983,carRequest.productionYear, "production year should be the one defined in the constructor")
    }

    @Test
    fun getPrice() {
        assertEquals(80000.00,carRequest.price,"price should be the one defined in the constructor")
    }

    @Test
    fun copy() {
        val b = carRequest.copy()
        assertAll("All values should remain the same",
            { assertEquals(b.model, carRequest.model) },
            { assertEquals(b.brand, carRequest.brand) },
            { assertEquals(b.productionYear, carRequest.productionYear) },
            { assertEquals(b.price, carRequest.price) }
        )
    }

    @Test
    fun testToString() {
        assertEquals(
            "CarRequest(model=Mustang, brand=Ford, productionYear=1983, price=80000.0)",
            carRequest.toString(),
            "ToString function should return all values formatted"
        )
    }

    @Test
    fun testHashCode() {
        val b = carRequest.copy()
        assertEquals(b.hashCode(), carRequest.hashCode(), "tohashcode should return the same value with equal objects")
    }

    @Test
    fun testEquals() {
        val b = carRequest.copy()
        assertTrue(b == carRequest,"Two equal objects should return true when compared")
    }

}