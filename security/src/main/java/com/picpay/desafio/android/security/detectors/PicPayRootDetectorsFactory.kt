package com.picpay.desafio.android.security.detectors

import android.content.Context

object PicPayRootDetectorsFactory {

    fun createDetectorsList(context: Context) : List<PicPayRootDetection> {
        return listOf(
            RootManagerAppsDetector(context),
            RootCloakingAppsDetector(context),
            RootMaliciousAppsDetector(context),
            DangerousPropsDetector(),
            TestKeyKernelDetector(),
            WritablePathsDetector(),
            SuCommandDetector(),
            BinarySuDetector(),
            BinaryBusyBoxDetector(),
            BinaryMagiskDetector()
        )
    }
}