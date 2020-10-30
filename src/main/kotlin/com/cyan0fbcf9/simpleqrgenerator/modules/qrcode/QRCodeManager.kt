package com.cyan0fbcf9.simpleqrgenerator.modules.qrcode

import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import java.io.ByteArrayOutputStream
import java.io.OutputStream
import javax.imageio.ImageIO

class QRCodeManager(private val size: Int = 512) {

    fun generate(url: String): ByteArray? {
        return try {
            val bitmap = QRCodeWriter().encode(url, BarcodeFormat.QR_CODE, size, size)
            val bufferedImage = MatrixToImageWriter.toBufferedImage(bitmap)
            ByteArrayOutputStream().apply {
                ImageIO.write(bufferedImage, "png", this)
            }.toByteArray()
        } catch (e: WriterException) {
            null
        }
    }
}