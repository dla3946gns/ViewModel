package com.example.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), OnClickListener {

    companion object {
        const val TAG: String = "로그"
    }

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        mainViewModel.currentValue.observe(this, Observer {
            Log.d(TAG, "MainActivity - mainViewModel - CurrentValue 라이브 데이터 값 변경 : ")
            binding.tvNumber.text = it.toString()
        })

        binding.btnPlus.setOnClickListener(this)
        binding.btnMinus.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (binding.etNumber.text.toString().isNotEmpty()) {
            val userInput: Int = binding.etNumber.text.toString().toInt()

            when(view) {
                binding.btnPlus ->
                    mainViewModel.updateValue(actionType = ActionType.PLUS, userInput)
                binding.btnMinus ->
                    mainViewModel.updateValue(actionType = ActionType.MINUS, userInput)
            }
        }
    }
}