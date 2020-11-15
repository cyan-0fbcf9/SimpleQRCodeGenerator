package com.cyan0fbcf9.simpleqrgenerator.errors

import com.cyan0fbcf9.simpleqrgenerator.models.errors.NoHandlerFoundData
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.NoHandlerFoundException

/*
NOTE: I haven't understood about spring framework yet. so, this code is commented out.
 */

//@RestController
//class ErrorHandlingController: ErrorController {
//
//    @RequestMapping("/error")
//    fun handleError(): ResponseEntity<String> {
//        return ResponseEntity("app error", HttpStatus.INTERNAL_SERVER_ERROR)
//    }
//
//    override fun getErrorPath(): String = "/error"
//}