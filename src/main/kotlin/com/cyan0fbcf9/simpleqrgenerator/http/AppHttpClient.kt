package com.cyan0fbcf9.simpleqrgenerator.http

import okhttp3.OkHttpClient

class AppHttpClient {
    val client = OkHttpClient()

    companion object {
        private var INSTANCE: AppHttpClient ? = null

        val shared: OkHttpClient
            get() {
                val tempInstance = this.INSTANCE
                if (tempInstance != null) {
                    return tempInstance.client
                }
                synchronized(this) {
                    val instance = AppHttpClient ()
                    this.INSTANCE = instance
                    return instance.client
                }
            }
    }
}