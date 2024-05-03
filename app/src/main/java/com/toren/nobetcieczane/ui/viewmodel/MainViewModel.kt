package com.toren.nobetcieczane.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toren.nobetcieczane.data.model.Pharmacy
import com.toren.nobetcieczane.data.network.ApiUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _pharmacyList = MutableLiveData<MutableList<Pharmacy>>()
    val pharmacyList : LiveData<MutableList<Pharmacy>> = _pharmacyList
    private val dao = ApiUtils.getPharmacyDao()


    fun getPharmacies(district: String, city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = dao.getPharmacy(district, city)
            if(response.isSuccessful) {
                launch(Dispatchers.Main) {
                response.body()?.result?.let {
                    _pharmacyList.postValue(it.toMutableList())
                    }
                }
            }
        }
    }
    fun getPharmacies(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = dao.getPharmacy(city)
            if (response.isSuccessful) {
                launch(Dispatchers.Main) {
                    response.body()?.result?.let{
                        _pharmacyList.postValue(it.toMutableList())
                    }
                }
            }
        }
    }



        /* // without coroutine
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
    */

}