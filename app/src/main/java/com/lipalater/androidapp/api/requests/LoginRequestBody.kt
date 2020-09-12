package com.lipalater.androidapp.api.requests

import com.google.gson.annotations.SerializedName
import com.lipalater.androidapp.util.*


class LoginRequestBody {
    @SerializedName(EMAIL)
    var myemail:String?=null
    @SerializedName(PASSWORD)
    var mypassword: String? = null
}
