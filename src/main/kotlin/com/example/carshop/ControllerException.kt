package com.example.carshop
import com.example.carshop.model.CarErrorMessage
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
@RestControllerAdvice
class CarControllerExceptionHandler {
    @ExceptionHandler(AccountNotFoundException::class)
    fun handleAccountNotFoundException(re: RuntimeException): ResponseEntity<CarErrorMessage> {
        return ResponseEntity<CarErrorMessage>(
            CarErrorMessage(re.message?:""),
            HttpStatus.CONFLICT
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<*> {
        val errors = e.bindingResult.fieldErrors.map { it.defaultMessage }.toList()
        return ResponseEntity(
            mapOf("errors" to errors),
            HttpHeaders(),
            HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(Exception::class)
    fun handleGenericException(e: Exception): ResponseEntity<CarErrorMessage> {
        return ResponseEntity<CarErrorMessage>(
            CarErrorMessage(e.message?:""),
            HttpStatus.INTERNAL_SERVER_ERROR
        )
    }
}