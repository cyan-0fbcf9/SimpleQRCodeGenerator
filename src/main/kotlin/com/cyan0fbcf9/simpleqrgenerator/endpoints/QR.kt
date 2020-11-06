package com.cyan0fbcf9.simpleqrgenerator.endpoints

import com.cyan0fbcf9.simpleqrgenerator.http.images.requestImage
import com.cyan0fbcf9.simpleqrgenerator.modules.qrcode.QRCodeManager
import com.cyan0fbcf9.simpleqrgenerator.services.AppCoroutineService
import com.google.zxing.NotFoundException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.HttpRequestMethodNotSupportedException
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/qr")
class QR(private val coroutineService: AppCoroutineService, private val qrManager: QRCodeManager) {
    @GetMapping("/generate", produces = [MediaType.IMAGE_PNG_VALUE])
    @ResponseBody
    fun generate(@RequestParam("url", required = false) url: String?,
                 @RequestParam("image_link", required = false) imageLink: String?): ByteArray = runBlocking {
        val image = withContext(coroutineService.coroutineContext + Dispatchers.IO) {
            requestImage(imageLink ?: "https://ja.nuxtjs.org/logos/nuxt-icon.png")
        }

        qrManager.generate(url ?: "https://cyan-0fbcf9.com", image = image) ?: ByteArray(0)
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleNotFoundException(): String = "Not Found"
}