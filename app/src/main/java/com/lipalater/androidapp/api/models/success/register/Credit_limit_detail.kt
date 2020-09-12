import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Credit_limit_detail (

    @SerializedName("id") val id : String,
    @SerializedName("available_limit") val available_limit : Int,
    @SerializedName("credit_limit") val credit_limit : Int,
    @SerializedName("credit_score_report") val credit_score_report : String,
    @SerializedName("credit_score_notes") val credit_score_notes : String,
    @SerializedName("credit_limit_status") val credit_limit_status : String,
    @SerializedName("credit_option") val credit_option : String,
    @SerializedName("approval_type") val approval_type : String,
    @SerializedName("customer_id") val customer_id : String,
    @SerializedName("approved_at") val approved_at : String,
    @SerializedName("limit_activated_at") val limit_activated_at : String,
    @SerializedName("deleted_at") val deleted_at : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String
)
