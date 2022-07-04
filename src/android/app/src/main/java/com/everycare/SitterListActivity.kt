package com.everycare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.everycare.adapter.SittersRecyclerViewAdapter
import com.everycare.databinding.ActivityMainBinding
import com.everycare.databinding.ActivitySitterListBinding
import com.everycare.dto.SitterListDTO
import com.everycare.viewModel.MainViewModel
import com.everycare.viewModel.SitterViewModel

class SitterListActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivitySitterListBinding.inflate(layoutInflater)
    }

    private var sitterList: ArrayList<SitterListDTO>? = null

    private val viewModel by viewModels<MainViewModel>()

    private val sitterViewModel by viewModels<SitterViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        sitterViewModel.getCareSitters()

        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager

        sitterViewModel.sitterList.observe(this) {
            sitterList = sitterViewModel.sitterList.value?.body

            val sitterRecyclerViewAdapter = SittersRecyclerViewAdapter(this, sitterList) { sitter->

            }

            binding.recyclerView.adapter = sitterRecyclerViewAdapter

        }

    }
}