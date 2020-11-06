package com.cyan0fbcf9.simpleqrgenerator.services

import com.cyan0fbcf9.simpleqrgenerator.operator.images.combine
import com.cyan0fbcf9.simpleqrgenerator.operator.images.duplicateChangedType
import com.cyan0fbcf9.simpleqrgenerator.operator.images.duplicateResizedImage
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.WriterException
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import org.springframework.stereotype.Service
import java.awt.Image
import java.awt.image.BufferedImage
import java.io.ByteArrayOutputStream
import java.lang.Exception
import javax.imageio.ImageIO
import kotlin.math.floor

@Service
class QRCodeService(private val size: Int = 512) {
    fun generate(url: String, image: Image? = null): ByteArray? {
        val qrBitmap = try {
            QRCodeWriter().encode(
                    url,
                    BarcodeFormat.QR_CODE,
                    size,
                    size,
                    mapOf<EncodeHintType, Any>(
                        EncodeHintType.MARGIN to 0,
                            EncodeHintType.ERROR_CORRECTION to ErrorCorrectionLevel.H,
                            EncodeHintType.QR_VERSION to 5
                    ))
        } catch (e: WriterException) {
            println("[app error] ${e.message}")
            return null
        }
        val qrBufferedImage = MatrixToImageWriter.toBufferedImage(qrBitmap).duplicateChangedType(BufferedImage.TYPE_3BYTE_BGR).apply {
            val resizedImage = (image as? BufferedImage)?.duplicateResizedImage(floor(size * 0.25).toInt(), floor(size * 0.25).toInt())
            if (resizedImage != null)
                combine(resizedImage)
        }

        return try {
            ByteArrayOutputStream().apply {
                ImageIO.write(qrBufferedImage, "png", this)
            }.toByteArray()
        } catch (e: Exception) {
            println("[app error] ${e.message}")
            return null
        }
    }
}