package com.everycare.api

import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

class Server {
    companion object{
        var url = "https://10.30.3.62:8086"
        private var server:Retrofit=Retrofit.Builder()
            .baseUrl(url)
            .client(getUnsafeOkHttpClient().build()) //SSL 우회
            .addConverterFactory(NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var api: Api = server.create(Api::class.java)

        /**
         * Retrofit SSL 우회 접속 통신
         */
        fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(chain: Array<out X509Certificate>?, authType: String?) {

                }

                override fun checkServerTrusted(chain: Array<out X509Certificate>?, authType: String?) {

                }

                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }
            })

            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())

            val sslSocketFactory = sslContext.socketFactory

            val builder = OkHttpClient.Builder()
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }

            return builder
        }
    }

    /**
     * 비어있는(length=0)인 Response를 받았을 경우 처리
     */
    class NullOnEmptyConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(type: Type?, annotations: Array<Annotation>?, retrofit: Retrofit?): Converter<ResponseBody, *>? {
            val delegate = retrofit!!.nextResponseBodyConverter<Any>(this, type!!, annotations!!)
            return Converter<ResponseBody, Any> {
                if (it.contentLength() == 0L) return@Converter EmptyResponse()
                delegate.convert(it)
            }
        }

        class EmptyResponse {

        }

    }


}