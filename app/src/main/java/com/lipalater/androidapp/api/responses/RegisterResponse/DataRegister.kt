import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class DataRegister (

    @SerializedName("id") val id : Int,
    @SerializedName("provider") val provider : String,
    @SerializedName("uid") val uid : String,
    @SerializedName("allow_password_change") val allow_password_change : Boolean,
    @SerializedName("name") val name : String,
    @SerializedName("nickname") val nickname : String,
    @SerializedName("image") val image : String,
    @SerializedName("email") val email : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("first_name") val first_name : String,
    @SerializedName("last_name") val last_name : String,
    @SerializedName("id_number") val id_number : Int,
    @SerializedName("phone_number") val phone_number : String,
    @SerializedName("date_of_birth") val date_of_birth : String,
    @SerializedName("marital_status") val marital_status : String,
    @SerializedName("gender") val gender : String,
    @SerializedName("employed") val employed : Boolean,
    @SerializedName("musoni_id") val musoni_id : String,
    @SerializedName("customer_standing") val customer_standing : String,
    @SerializedName("status") val status : Int,
    @SerializedName("countries_id") val countries_id : String,
    @SerializedName("deleted_at") val deleted_at : String,
    @SerializedName("employed_occupational_details") val employed_occupational_details : List<Employed_occupational_details>,
    @SerializedName("self_employed_occupational_details") val self_employed_occupational_details : List<String>,
    @SerializedName("loan_application_detail") val loan_application_detail : Loan_application_detail
)
