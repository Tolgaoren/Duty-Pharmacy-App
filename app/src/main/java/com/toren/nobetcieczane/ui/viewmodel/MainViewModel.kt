package com.toren.nobetcieczane.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.toren.nobetcieczane.data.model.Pharmacy
import com.toren.nobetcieczane.data.model.PharmacyResponse
import com.toren.nobetcieczane.data.network.ApiUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val _pharmacyList = MutableLiveData<MutableList<Pharmacy>>()
    val pharmacyList : LiveData<MutableList<Pharmacy>> = _pharmacyList


    fun getPharmacies(district: String, city: String) {
        val dao = ApiUtils.getPharmacyDao()
        val data = dao.getPharmacy(district, city)

        data.enqueue(object : Callback<PharmacyResponse> {
            override fun onResponse(call: Call<PharmacyResponse>,
                                    response: Response<PharmacyResponse>) {
                response.body()?.let {
                    _pharmacyList.postValue(it.result.toMutableList())
                }
            }
            override fun onFailure(p0: Call<PharmacyResponse>, p1: Throwable) {
                Log.d("response","fail")
            }
        })
    }
}