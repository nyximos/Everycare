package com.everycare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.everycare.databinding.ActivityMainBinding
import com.everycare.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding.btnLogin.setOnClickListener {
            var username = binding.username.text
            var password = binding.password.text


            if (username.isNullOrEmpty()) {
                Toast.makeText(this, "아이디를 입력하세요", Toast.LENGTH_SHORT).show()
            }
            if (password.isNullOrEmpty()) {
                Toast.makeText(this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show()
            }
            if(username!!.isNotEmpty() && password!!.isNotBlank()) {
                viewModel.login(username.toString(), password.toString())
                if (viewModel.loginStatus==1){
                    
                }
            }
        }

    }
}