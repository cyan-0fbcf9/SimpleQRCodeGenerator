package com.cyan0fbcf9.simpleqrgenerator.endpoints

import com.cyan0fbcf9.simpleqrgenerator.http.images.requestImage
import com.cyan0fbcf9.simpleqrgenerator.services.QRCodeService
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

        return qrCodeService.generate(url, image = image) ?: ByteArray(0)
    }
}