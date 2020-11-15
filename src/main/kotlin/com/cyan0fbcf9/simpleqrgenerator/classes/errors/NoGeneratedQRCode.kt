package com.cyan0fbcf9.simpleqrgenerator.classes.errors

class NoGeneratedQRCode: Error() {
    override val message: String?
        get() = "no generated qr code"
}