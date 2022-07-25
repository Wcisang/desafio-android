package com.picpay.desafio.android.security.detectors

import com.picpay.desafio.android.security.detectors.utils.DetectionUtils

class BinaryMagiskDetector : PicPayRootDetection {

    override fun check(): Boolean {
        return DetectionUtils.checkForBinary("magisk")
    }
}