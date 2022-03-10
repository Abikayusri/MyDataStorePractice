package binar.academy.mydatastorepractice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import binar.academy.mydatastorepractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var pref: CounterDataStoreManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pref = CounterDataStoreManager(this)
        viewModel = ViewModelProvider(this, ViewModelFactory(pref))[MainViewModel::class.java]

        setupView()
        setObserver()
    }

    private fun setupView() {
        binding.apply {
            btnIncrease.setOnClickListener {
                incrementCount()
            }
            btnDecrease.setOnClickListener {
                decrementCount()
            }

            btnSet.setOnClickListener {
                viewModel.saveDataStore(binding.tvValue.text.toString().toInt())
            }
        }
    }

    private fun incrementCount() {
        viewModel.incrementCount()
    }

    private fun decrementCount() {
        viewModel.decrementCount()
    }

    private fun setObserver() {
        viewModel.apply {
            getDataStore().observe(this@MainActivity) {
                binding.tvValue.text = it.toString()
            }

            vCounter.observe(this@MainActivity) { result ->
                binding.tvValue.text = result.toString()
            }
        }
    }
}