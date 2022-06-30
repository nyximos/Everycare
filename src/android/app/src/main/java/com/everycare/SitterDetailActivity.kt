package com.everycare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.everycare.databinding.ActivityMainBinding
import com.everycare.viewModel.MainViewModel
import com.everycare.viewModel.SitterViewModel

class SitterDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    private val sitterViewModel by viewModels<SitterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sitter_detail)
    }
}