package com.toren.nobetcieczane.data.model

import com.google.gson.annotations.Expose

data class Pharmacy(
    @Expose
    var name : String,
    @Expose
    var dist : String,
    @Expose
    var address : String,
    @Expose
    var phone : String,
    @Expose
    var loc : String
)
