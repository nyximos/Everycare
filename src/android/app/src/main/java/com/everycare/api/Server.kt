package com.everycare.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Server {
    companion object{
        var url = "http://10.30.3.62:808"
        private var server:Retrofit=Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        var api: Api = server.create(Api::class.java)

        fun changeUrl() {
            server = Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            api = server.create(Api::class.java)
        }

    }
}