package com.picpay.desafio.android.security.detectors

import android.os.Build

class TestKeyKernelDetector : PicPayRootDetection {

    override fun check(): Boolean {
        val buildTags = Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }
}