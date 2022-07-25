package com.picpay.desafio.android.security.detectors

import android.content.Context
import com.picpay.desafio.android.security.detectors.utils.DetectionUtils

class RootCloakingAppsDetector(private val context: Context) : PicPayRootDetection {

    private val rootAppsPackage = listOf(
        "com.koushikdutta.rommanager",
        "com.koushikdutta.rommanager.license",
        "com.dimonvideo.luckypatcher",
        "com.chelpus.lackypatch",
        "com.ramdroid.appquarantine",
        "com.ramdroid.appquarantinepro",
        "com.android.vending.billing.InAppBillingService.COIN",
        "com.chelpus.luckypatcher"
    )

    override fun check(): Boolean {
        return DetectionUtils.isAppListInstalled(context, rootAppsPackage)
    }
}