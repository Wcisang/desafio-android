package com.picpay.desafio.android.security.detectors.utils

import android.content.Context
import android.content.pm.PackageManager
import java.io.File

object DetectionUtils {

    private val suPaths = listOf(
        "/data/local/",
        "/data/local/bin/",
        "/data/local/xbin/",
        "/sbin/",
        "/su/bin/",
        "/system/bin/",
        "/system/bin/.ext/",
        "/system/bin/failsafe/",
        "/system/sd/xbin/",
        "/system/usr/we-need-root/",
        "/system/xbin/",
        "/cache",
        "/data",
        "/dev"
    )

    fun isAppListInstalled(context: Context, packages: List<String>): Boolean {
        var result = false
        val pm: PackageManager = context.packageManager
        for (packageName in packages) {
            try {
                pm.getPackageInfo(packageName, 0)
                result = true
            } catch (e: PackageManager.NameNotFoundException) {
            }
        }
        return result
    }

    fun checkForBinary(filename: String): Boolean {
        var result = false
        for (path in suPaths) {
            val f = File(path, filename)
            val fileExists: Boolean = f.exists()
            if (fileExists) {
                result = true
            }
        }
        return result
    }
}