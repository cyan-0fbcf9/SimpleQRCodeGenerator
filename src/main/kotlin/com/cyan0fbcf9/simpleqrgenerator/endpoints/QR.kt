package com.cyan0fbcf9.simpleqrgenerator.endpoints

import com.cyan0fbcf9.simpleqrgenerator.classes.errors.NoGeneratedQRCode
import com.cyan0fbcf9.simpleqrgenerator.http.images.requestImage
import com.cyan0fbcf9.simpleqrgenerator.services.QRCodeService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/qr")
class QR(private val qrCodeService: QRCodeService) {
    @GetMapping("/generate", produces = [MediaType.IMAGE_PNG_VALUE])
    @ResponseBody
    fun generate(@RequestParam("value", required = true) url: String,
                 @RequestParam("image", required = false) imageLink: String?): ByteArray {
        val image = if (imageLink != null)
            requestImage(imageLink)
        else
            null

        return qrCodeService.generate(url, image = image) ?: throw NoGeneratedQRCode()
    }

    @ExceptionHandler(NoGeneratedQRCode::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleNoGeneratedQRCode(exception: NoGeneratedQRCode): Map<String, Any?> = mapOf("message" to exception.message, "status" to HttpStatus.BAD_REQUEST)
}