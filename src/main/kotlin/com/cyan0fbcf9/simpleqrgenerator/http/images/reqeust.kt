package com.cyan0fbcf9.simpleqrgenerator.http.images

import com.cyan0fbcf9.simpleqrgenerator.http.AppHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.Request
import java.awt.image.BufferedImage
import javax.imageio.ImageIO

fun requestImage(fromLink: String): BufferedImage? {
    val request = Request.Builder().apply {
        url(fromLink)
    }.build()

    val response = try {
        AppHttpClient.shared.newCall(request).execute()
    } catch (e: Exception) {
        println("[app error] on http request. ${e.message}")
        null
    }

    return try {
        ImageIO.read(response?.body?.byteStream())
    } catch (e: Exception) {
        println("[app error] on loading image. ${e.message}")
        return null
    }
}