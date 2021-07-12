package com.mrz.irankart.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class MovieDetailResponse {
    @SerializedName("Response")
    @Expose
    var response: String? = null

    @SerializedName("Error")
    @Expose
    var error: String? = null
}
