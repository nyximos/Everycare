package com.everycare.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everycare.api.Server
import com.everycare.dto.LoginDTO
import eu.tutorials.anonymousboard.dto.ResponseDTO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response

class MainViewModel : ViewModel() {

    val error = MutableLiveData<String>()
    val id = MutableLiveData<String>()

    fun login(username: String, password: String) = viewModelScope.launch {

        val loginDTO = LoginDTO(username, password)
        val request = Server.api.login(loginDTO)
        request.enqueue(object : retrofit2.Callback<ResponseDTO> {
            override fun onResponse(call: Call<ResponseDTO>, response: Response<ResponseDTO>) {
                Log.d("RESPONSE", "성공 : ${response.raw()}")
                id.value= username
                Log.d("RESPONSE", "성공 : $id")

            }

            override fun onFailure(call: Call<ResponseDTO>, t: Throwable) {
                Log.d("RESPONSE", " 실패 : ${t.message}")
                error.value = t.localizedMessage
            }

        })
    }

}