package com.example.ct

import org.fluttercode.datafactory.impl.DataFactory
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

class BaseFactory {

    companion object {
        private val FACTORY = DataFactory()

        fun randomString(): String {
            return FACTORY.getRandomChars(32)
        }

        fun randomInt(): Int {
            return FACTORY.getNumberBetween(1, 100)
        }

        fun randomBoolean(): Boolean {
            return FACTORY.chance(50)
        }

        @Throws(InterruptedException::class)
        fun <T> getValue(clazz: T): T {
            val data = arrayOfNulls<Any>(1)
            val latch = CountDownLatch(1)

            latch.await(2, TimeUnit.SECONDS)

            return data[0] as T
        }
    }
}