package com.picpay.desafio.android.security.detectors

import android.content.Context
import com.picpay.desafio.android.security.detectors.utils.DetectionUtils

class RootManagerAppsDetector(private val context: Context) : PicPayRootDetection {

    private val rootAppsPackage = listOf(
        "com.noshufou.android.su",
        "com.noshufou.android.su.elite",
        "eu.chainfire.supersu",
        "com.koushikdutta.superuser",
        "com.thirdparty.superuser",
        "com.yellowes.su",
        "com.topjohnwu.magisk"
    )

    override fun check(): Boolean {
        return DetectionUtils.isAppListInstalled(context, rootAppsPackage)
    }
}