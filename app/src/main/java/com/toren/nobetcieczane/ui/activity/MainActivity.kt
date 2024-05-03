package com.toren.nobetcieczane.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.toren.nobetcieczane.ui.adapter.PharmacyAdapter
import com.toren.nobetcieczane.databinding.ActivityMainBinding
import com.toren.nobetcieczane.ui.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val pharmacyAdapter = PharmacyAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        observeViewModel()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = pharmacyAdapter
            setHasFixedSize(true)
            itemAnimator
        }

        binding.apply {
            searchBtn.setOnClickListener {
                val city = cityET.text.toString().trim()
                val dist = distET.text.toString().trim()
                if (city.isNotEmpty() && dist.isNotEmpty()) {
                    viewModel.getPharmacies(dist, city)
                }
                else if (city.isNotEmpty() && dist.isEmpty()) {
                    viewModel.getPharmacies(city)
                }
                else {
                    Log.d("search", "city null")
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun observeViewModel() {
        viewModel.pharmacyList.observe(this) { result ->
            result?.let {
                pharmacyAdapter.updatePharmacies(it)
            }
        }
    }


}

