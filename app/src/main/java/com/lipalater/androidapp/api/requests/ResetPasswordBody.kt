package com.lipalater.androidapp.api.requests

import com.google.gson.annotations.SerializedName
import com.lipalater.androidapp.util.*


class ResetPasswordBody {
    @SerializedName(EMAIL)
    var email:String?=null

}
