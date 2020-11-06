package com.cyan0fbcf9.simpleqrgenerator.errors

import com.cyan0fbcf9.simpleqrgenerator.models.errors.NoHandlerFoundData
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class ErrorHandlingController {
    @ExceptionHandler(NoHandlerFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun notFoundException(): NoHandlerFoundData = NoHandlerFoundData(message = "Not handler found.")
}