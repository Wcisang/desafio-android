package com.picpay.desafio.android.security.detectors

import java.io.IOException
import java.util.*
import kotlin.NoSuchElementException

class DangerousPropsDetector : PicPayRootDetection {

    private val propsMap = mapOf(
        Pair("ro.debuggable", "1"),
        Pair("ro.secure", "0")
    )

    override fun check(): Boolean {
        var result = false

        val lines: Array<String> = propsReader()
            ?: return result

        for (line in lines) {
            for (key in propsMap.keys) {
                if (line.contains(key)) {
                    var badValue: String? = propsMap[key]
                    badValue = "[$badValue]"
                    if (line.contains(badValue)) {
                        result = true
                    }
                }
            }
        }
        return result
    }

    private fun propsReader(): Array<String>? {
        return try {
            val inputstream = Runtime.getRuntime().exec("getprop").inputStream
                ?: return null
            val propVal: String = Scanner(inputstream).useDelimiter("\\A").next()
            propVal.split("\n").toTypedArray()
        } catch (e: IOException) {
            e.printStackTrace()
            null
        } catch (e: NoSuchElementException) {
            e.printStackTrace()
            null
        }
    }
}