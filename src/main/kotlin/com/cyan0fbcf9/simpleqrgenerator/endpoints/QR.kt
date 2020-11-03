package com.cyan0fbcf9.simpleqrgenerator.endpoints

import com.cyan0fbcf9.simpleqrgenerator.http.images.requestImage
import com.cyan0fbcf9.simpleqrgenerator.modules.qrcode.QRCodeManager
import com.cyan0fbcf9.simpleqrgenerator.services.AppCoroutineService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/qr")
class QR(private val coroutineService: AppCoroutineService) {
    val qrManager = QRCodeManager()

    @GetMapping("/generate", produces = [MediaType.IMAGE_PNG_VALUE])
    @ResponseBody
    fun generate(@RequestParam("url", required = false) url: String?, @RequestParam("image_link", required = false) imageLink: String?): ByteArray = runBlocking {
        val image = coroutineService.async(Dispatchers.IO) {
            requestImage(imageLink ?: "https://ja.nuxtjs.org/logos/nuxt-icon.png")
        }.await()

        qrManager.generate(url ?: "https://cyan-0fbcf9.com", image = image) ?: ByteArray(0)
    }
}