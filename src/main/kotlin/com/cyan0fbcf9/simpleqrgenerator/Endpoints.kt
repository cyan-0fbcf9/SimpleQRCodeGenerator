package com.cyan0fbcf9.simpleqrgenerator

import com.cyan0fbcf9.simpleqrgenerator.modules.qrcode.QRCodeManager
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
class Endpoints {
    val qrManager = QRCodeManager()

    @GetMapping("qr/generate", produces = [MediaType.IMAGE_PNG_VALUE])
    @ResponseBody
    fun generateQRCode(): ByteArray {
        return qrManager.generate("https://google.com") ?: ByteArray(0)
    }
}