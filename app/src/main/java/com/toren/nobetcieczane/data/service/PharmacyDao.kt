package com.toren.nobetcieczane.data.service

import com.toren.nobetcieczane.BuildConfig
import com.toren.nobetcieczane.data.model.PharmacyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PharmacyDao {

    @Headers("content-type: application/json",
        "authorization: ${BuildConfig.API_KEY}")
    @GET("health/dutyPharmacy")
    fun getPharmacy(@Query("ilce")ilce : String, @Query("il")il: String) : Call<PharmacyResponse>


}