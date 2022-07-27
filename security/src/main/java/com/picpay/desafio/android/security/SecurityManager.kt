package com.picpay.desafio.android.security

import android.content.Context
import com.picpay.desafio.android.security.certificate.SignCertificateValidation
import com.picpay.desafio.android.security.detectors.PicPayRootDetectorsFactory

class SecurityManager(private val context: Context) {

    fun isASafeDevice() : Boolean {
        return !isRooted()
    }

    private fun isRooted() : Boolean {
        val detectorsList = PicPayRootDetectorsFactory.createDetectorsList(context)
        for (detector in detectorsList) {
            detector.check()
        }
        val signValidator = SignCertificateValidation()
        val isSigned = signValidator.validateAppSignature(context)
        return false
    }
}