package com.cyan0fbcf9.simpleqrgenerator.operator.images

import java.awt.image.BufferedImage

fun BufferedImage.duplicateChangedType(type: Int): BufferedImage {
    return BufferedImage(this.width, this.height, type).also {
        it.createGraphics().drawImage(this, 0, 0, null)
    }
}