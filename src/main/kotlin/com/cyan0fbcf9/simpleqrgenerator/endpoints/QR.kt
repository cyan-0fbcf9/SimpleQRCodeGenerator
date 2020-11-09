package com.cyan0fbcf9.simpleqrgenerator.endpoints

import com.cyan0fbcf9.simpleqrgenerator.http.images.requestImage
import com.cyan0fbcf9.simpleqrgenerator.services.QRCodeService
import com.cyan0fbcf9.simpleqrgenerator.services.AppCoroutineService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/qr")
class QR(private val coroutineService: AppCoroutineService, private val qrCodeService: QRCodeService) {
    @GetMapping("/generate", produces = [MediaType.IMAGE_PNG_VALUE])
    @ResponseBody
    fun generate(@RequestParam("value", required = false) url: String?,
                 @RequestParam("image", required = false) imageLink: String?): ByteArray = runBlocking {
        val image = withContext(coroutineService.coroutineContext + Dispatchers.IO) {
            if (imageLink != null)
                requestImage(imageLink)
            else
                null
        }

        qrCodeService.generate(url ?: "https://cyan-0fbcf9.com", image = image) ?: ByteArray(0)
    }
}