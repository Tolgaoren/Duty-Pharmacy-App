package com.toren.nobetcieczane.data.service

import com.toren.nobetcieczane.BuildConfig
import com.toren.nobetcieczane.data.model.PharmacyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PharmacyDao {

    @Headers("content-type: application/json",
        "authorization: ${BuildConfig.API_KEY}")
    @GET("health/dutyPharmacy")
    suspend fun getPharmacy(@Query("ilce")ilce : String, @Query("il")il: String) : Response<PharmacyResponse>

    @Headers("content-type: application/json",
        "authorization: ${BuildConfig.API_KEY}")
    @GET("health/dutyPharmacy")
    suspend fun getPharmacy(@Query("il")il: String) : Response<PharmacyResponse>

    /*  withoutCoroutine
    @Headers("content-type: application/json",
        "authorization: ${BuildConfig.API_KEY}")
    @GET("health/dutyPharmacy")
    fun getPharmacy(@Query("ilce")ilce : String, @Query("il")il: String) : Call<PharmacyResponse>
*/

}