package com.toren.nobetcieczane.ui.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.toren.nobetcieczane.BuildConfig
import com.toren.nobetcieczane.ui.adapter.PharmacyAdapter
import com.toren.nobetcieczane.data.network.ApiUtils
import com.toren.nobetcieczane.databinding.ActivityMainBinding
import com.toren.nobetcieczane.data.model.Pharmacy
import com.toren.nobetcieczane.data.model.PharmacyResponse
import com.toren.nobetcieczane.ui.viewmodel.MainViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel
    private val pharmacyAdapter = PharmacyAdapter(mutableListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = pharmacyAdapter
            setHasFixedSize(true)
            itemAnimator
        }

        observeViewModel()
        viewModel.getPharmacies("Çankaya", "Ankara")
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