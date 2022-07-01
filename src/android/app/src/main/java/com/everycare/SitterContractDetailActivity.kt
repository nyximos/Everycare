package com.everycare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.everycare.databinding.ActivitySitterContractDetailBinding
import com.everycare.viewModel.MainViewModel
import com.everycare.viewModel.SitterViewModel

class SitterContractDetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySitterContractDetailBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    private val sitterViewModel by viewModels<SitterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sitter_contract_detail)
    }
}