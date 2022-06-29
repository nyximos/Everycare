package com.everycare.api

import android.telecom.Call
import com.everycare.dto.LoginDTO
import eu.tutorials.anonymousboard.dto.ResponseDTO
import retrofit2.http.Body
import retrofit2.http.POST

interface Api {

    @POST("/login")
    fun login(@Body loginDTO: LoginDTO): Call<ResponseDTO>
}