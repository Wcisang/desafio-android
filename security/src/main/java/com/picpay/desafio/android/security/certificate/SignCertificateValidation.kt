package com.picpay.desafio.android.security.certificate

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.picpay.desafio.android.Secrets
import java.security.MessageDigest


class SignCertificateValidation {

    @Throws(PackageManager.NameNotFoundException::class)
    fun validateAppSignature(context: Context): Boolean {
        val packageInfo: PackageInfo = context.packageManager.getPackageInfo(
            context.packageName, PackageManager.GET_SIGNATURES
        )
        val key = Secrets().getsignappkey("com.picpay.desafio.android")
        for (signature in packageInfo.signatures) {
            val sha1 = getSHA1(signature.toByteArray())
            return key == sha1
        }
        return false
    }

    companion object {

        fun getSHA1(sig: ByteArray?): String {
            val digest: MessageDigest = MessageDigest.getInstance("SHA1")
            digest.update(sig)
            val hashtext: ByteArray = digest.digest()
            return bytesToHex(hashtext)
        }

        private fun bytesToHex(bytes: ByteArray): String {
            val hexArray = charArrayOf(
                '0', '1', '2', '3', '4', '5', '6', '7', '8',
                '9', 'A', 'B', 'C', 'D', 'E', 'F'
            )
            val hexChars = CharArray(bytes.size * 2)
            var v: Int
            for (j in bytes.indices) {
                v = bytes[j].toInt() and 0xFF
                hexChars[j * 2] = hexArray[v ushr 4]
                hexChars[j * 2 + 1] = hexArray[v and 0x0F]
            }
            return String(hexChars)
        }
    }
}