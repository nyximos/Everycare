package com.everycare.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.everycare.api.Server
import com.everycare.dto.SitterListDTO
import com.everycare.dto.ResponseCareSitterListDTO
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SitterViewModel : ViewModel()  {

    val error = MutableLiveData<String>()
    val sitterList = MutableLiveData<ResponseCareSitterListDTO>()

    fun getCareSitters() = viewModelScope.launch {
        val request = Server.api.findCareSitters()
        request.enqueue(object : Callback<ResponseCareSitterListDTO>{
            override fun onResponse(
                call: Call<ResponseCareSitterListDTO>,
                response: Response<ResponseCareSitterListDTO>
            ) {
                if(response.isSuccessful) {
                    sitterList.value = response.body()
                    Log.i("RESPONSE", "전체 조회 성공 : ${response.body()?.body}")

                }
            }

            override fun onFailure(call: Call<ResponseCareSitterListDTO>, t: Throwable) {
                error.value = t.localizedMessage
                Log.d("RESPONSE", "실패 : $t")
            }

        })
    }

}