package com.cyan0fbcf9.simpleqrgenerator.services

import kotlinx.coroutines.*
import org.springframework.stereotype.Service
import kotlin.coroutines.CoroutineContext

@Service
class AppCoroutineService: CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + this.job


}