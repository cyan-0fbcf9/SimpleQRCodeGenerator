package com.cyan0fbcf9.simpleqrgenerator.operator.images

import java.awt.Image
import java.awt.Point
import java.awt.image.BufferedImage

fun BufferedImage.combine(topImage: Image) {
    val leftTopPoint = Point((this.width - topImage.getWidth(null)) / 2, (this.height - topImage.getHeight(null)) / 2)
    this.createGraphics().drawImage(topImage, leftTopPoint.x, leftTopPoint.y, null)
}
