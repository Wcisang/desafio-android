package com.picpay.desafio.android.security.detectors

import java.io.IOException
import java.util.*
import kotlin.NoSuchElementException

class WritablePathsDetector : PicPayRootDetection {

    private val paths = listOf(
        "/system",
        "/system/bin",
        "/system/sbin",
        "/system/xbin",
        "/vendor/bin",
        "/sbin",
        "/etc"
    )

    override fun check(): Boolean {

        var result = false
        val lines: Array<String> = mountReader()
            ?: return result

        for (line in lines) {
            val args = line.split(" ").toTypedArray()
            if (args.size < 4) {
                continue
            }
            val mountPoint = args[1]
            val mountOptions = args[3]
            for (pathToCheck in paths) {
                if (mountPoint.equals(pathToCheck, ignoreCase = true)) {
                    for (option in mountOptions.split(",").toTypedArray()) {
                        if (option.equals("rw", ignoreCase = true)) {
                            result = true
                            break
                        }
                    }
                }
            }
        }

        return result
    }

    private fun mountReader(): Array<String>? {
        return try {
            val inputStream = Runtime.getRuntime().exec("mount").inputStream
                ?: return null
            val propVal: String = Scanner(inputStream).useDelimiter("\\A").next()
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