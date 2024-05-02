package com.toren.nobetcieczane.data.network

import com.toren.nobetcieczane.data.service.PharmacyDao

class ApiUtils {


    companion object {

        val BASE_URL = "https://api.collectapi.com/"

        fun getPharmacyDao() : PharmacyDao {
            return RetrofitClient.getClient(BASE_URL).create(PharmacyDao::class.java)
        }
    }

}