package com.everycare.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SitterViewModel : ViewModel()  {

    val error = MutableLiveData<String>()

}