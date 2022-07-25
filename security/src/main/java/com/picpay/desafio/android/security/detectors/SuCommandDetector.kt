package com.picpay.desafio.android.security.detectors

import java.io.BufferedReader
import java.io.InputStreamReader

class SuCommandDetector : PicPayRootDetection {

    override fun check(): Boolean {
        var process: Process? = null
        return try {
            process = Runtime.getRuntime().exec(arrayOf("which", "su"))
            val cmd = BufferedReader(InputStreamReader(process.inputStream))
            cmd.readLine() != null
        } catch (t: Throwable) {
            false
        } finally {
            process?.destroy()
        }
    }
}