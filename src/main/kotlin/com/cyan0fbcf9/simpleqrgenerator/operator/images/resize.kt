package com.cyan0fbcf9.simpleqrgenerator.operator.images

import java.awt.Image
import java.awt.image.BufferedImage

/**
 * リサイズした BufferedImage インスタンスを作成する
 * @param width リサイズ後の横幅
 * @param height リサイズ後の縦幅
 * @return リサイズされた BufferedImage インスタンス
 */
fun BufferedImage.duplicateResizedImage(width: Int, height: Int): BufferedImage {
    val tempImage = BufferedImage(width, height, this.type)
    val resizedImage = this.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING)
    tempImage.createGraphics().drawImage(resizedImage, 0, 0, null)

    return tempImage
}