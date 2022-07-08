//package com.everycare.api
//
//import android.content.Context
//import android.os.Build
//import androidx.annotation.RequiresApi
//import com.everycare.R
//import okhttp3.OkHttpClient
//import java.io.IOException
//import java.io.InputStream
//import java.security.KeyManagementException
//import java.security.KeyStore
//import java.security.KeyStoreException
//import java.security.NoSuchAlgorithmException
//import java.security.cert.Certificate
//import java.security.cert.CertificateException
//import java.security.cert.CertificateFactory
//import java.security.cert.X509Certificate
//import javax.net.ssl.SSLContext
//import javax.net.ssl.TrustManagerFactory
//import javax.net.ssl.X509TrustManager
//
//@RequiresApi(Build.VERSION_CODES.N)
//class SelfSigningHelper constructor(context: Context
//) {
//    fun setSSLOkHttp(builder: OkHttpClient.Builder): OkHttpClient.Builder {
//        builder.sslSocketFactory(
//            sslContext.socketFactory,
//            tmf.trustManagers[0] as X509TrustManager
//        )
//
//        return builder
//    }
//
//    companion object {
//        private var instance: SelfSigningHelper? = null
//
//        private lateinit var context: Context
//
//        fun getInstance(): SelfSigningHelper {
//            return instance ?: synchronized(this) {
//                instance ?: SelfSigningHelper(context).also {
//                    context = context
//                    instance = it
//                }
//            }
//        }
//
//        lateinit var tmf: TrustManagerFactory
//        lateinit var sslContext: SSLContext
//
//        init {
//            val cf: CertificateFactory
//            val ca: Certificate
//
//            val caInput: InputStream
//
//            try {
//                cf = CertificateFactory.getInstance("X.509")
//
//                caInput = context.resources.openRawResource(R.raw.server)
//
//                ca = cf.generateCertificate(caInput)
//                println("ca = ${(ca as X509Certificate).subjectDN}")
//
//                // Create a KeyStore containing our trusted CAs
//                val keyStoreType = KeyStore.getDefaultType()
//                val keyStore = KeyStore.getInstance(keyStoreType)
//                with(keyStore) {
//                    load(null, null)
//                    keyStore.setCertificateEntry("ca", ca)
//                }
//
//                // Create a TrustManager that trusts the CAs in our KeyStore
//                val tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm()
//                tmf = TrustManagerFactory.getInstance(tmfAlgorithm)
//                tmf.init(keyStore)
//
//                // Create an SSLContext that uses our TrustManager
//                sslContext = SSLContext.getInstance("TLS")
//                sslContext.init(null, tmf.trustManagers, java.security.SecureRandom())
//
//                caInput.close()
//
//            } catch (e: KeyStoreException) {
//                e.printStackTrace()
//            } catch (e: CertificateException) {
//                e.printStackTrace()
//            } catch (e: NoSuchAlgorithmException) {
//                e.printStackTrace()
//            } catch (e: KeyManagementException) {
//                e.printStackTrace()
//            } catch (e: IOException) {
//                e.printStackTrace()
//            }
//        }
//
//
//    }
//}