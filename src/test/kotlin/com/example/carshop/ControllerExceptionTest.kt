package com.example.carshop

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class CarControllerExceptionTest {
    private val carControllerExceptionHandler = CarControllerExceptionHandler()

    @Test
    fun handleAccountNotFoundException() {
        val re:RuntimeException = mockk()
        every { re.message } returns "error"
        val response = carControllerExceptionHandler.handleAccountNotFoundException(re)
        assertEquals(response.body?.message, "error")
        assertEquals(response.statusCode, HttpStatus.CONFLICT)
    }
    @Test
    fun handleGenericException() {
        val re:RuntimeException = mockk()
        every { re.message } returns "error"
        val response = carControllerExceptionHandler.handleGenericException(re)
        assertEquals(response.body?.message, "error")
        assertEquals(response.statusCode, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}