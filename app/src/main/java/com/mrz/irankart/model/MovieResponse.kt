package com.mrz.irankart.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieResponse<T> {
    @SerializedName("Search")
    @Expose
    var search: List<T>? = null

    @SerializedName("totalResults")
    @Expose
    var totalResults: String? = null

    @SerializedName("Response")
    @Expose
    var response: String? = null
}