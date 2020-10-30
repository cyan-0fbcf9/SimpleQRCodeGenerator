package com.cyan0fbcf9.simpleqrgenerator.endpoints

import com.cyan0fbcf9.simpleqrgenerator.modules.qrcode.QRCodeManager
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/qr")
class QR {
    val qrManager = QRCodeManager()

    @GetMapping("/generate", produces = [MediaType.IMAGE_PNG_VALUE])
    @ResponseBody
    fun generateQRCode(@RequestParam("url", required = false) url: String?): ByteArray {
        return qrManager.generate(url ?: "https://cyan-0fbcf9.com") ?: ByteArray(0)
    }
}