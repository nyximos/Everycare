//package com.everycare.api
//
//import android.content.Context
//import com.bumptech.glide.Glide
//import com.bumptech.glide.Registry
//import com.bumptech.glide.annotation.GlideModule
//import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
//import com.bumptech.glide.load.model.GlideUrl
//import com.bumptech.glide.module.AppGlideModule
//import okhttp3.OkHttpClient
//import java.io.InputStream
//
//
//@GlideModule
//class MyGlideApp: AppGlideModule() {
//
//
//    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
//        super.registerComponents(context, glide, registry)
//
//        // OkHttpClient Builder에
//        // 자체인증서 HTTPS 통신하도록 설정
//
//        val helper: SelfSigningHelper = SelfSigningHelper.getInstance()
//        val builder = OkHttpClient.Builder()
//        helper.setSSLOkHttp(builder)
//
//        registry.replace(
//            GlideUrl::class.java,
//            InputStream::class.java,
//            OkHttpUrlLoader.Factory(builder.build())
//        )
//
//
//    }
//}
//
