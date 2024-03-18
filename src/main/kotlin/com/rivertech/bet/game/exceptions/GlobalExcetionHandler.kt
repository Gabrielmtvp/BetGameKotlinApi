package com.rivertech.bet.game.exceptions

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.stream.Collectors

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, List<String?>>> {
        val errors = ex.bindingResult.fieldErrors
            .stream().map { obj: FieldError -> obj.defaultMessage }.collect(Collectors.toList())
        return ResponseEntity(getErrorsMap(errors), HttpHeaders(), HttpStatus.BAD_REQUEST)
    }

    private fun getErrorsMap(errors: List<String?>): Map<String, List<String?>> {
        val errorResponse: MutableMap<String, List<String?>> = HashMap()
        errorResponse["errors"] = errors
        return errorResponse
    }
}