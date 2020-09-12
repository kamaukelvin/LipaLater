import com.google.gson.annotations.SerializedName

/*
Copyright (c) 2020 Kotlin Data Classes Generated from JSON powered by http://www.json2kotlin.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

For support, please feel free to contact me at https://www.linkedin.com/in/syedabsar */


data class Loan_application_detail (

    @SerializedName("id") val id : Int,
    @SerializedName("credit_limit") val credit_limit : Int,
    @SerializedName("crb_report_type") val crb_report_type : String,
    @SerializedName("used_credit_limit") val used_credit_limit : Boolean,
    @SerializedName("loan_application_status") val loan_application_status : String,
    @SerializedName("credit_limit_status") val credit_limit_status : String,
    @SerializedName("created_at") val created_at : String,
    @SerializedName("updated_at") val updated_at : String,
    @SerializedName("customer_id") val customer_id : Int,
    @SerializedName("musoni_loan_id") val musoni_loan_id : String,
    @SerializedName("max_limit") val max_limit : Int,
    @SerializedName("assignee") val assignee : String,
    @SerializedName("cold_call") val cold_call : Boolean,
    @SerializedName("musoni_outstanding_balance") val musoni_outstanding_balance : String,
    @SerializedName("credit_option") val credit_option : String,
    @SerializedName("referral_source") val referral_source : String,
    @SerializedName("musoni_outstanding_principal") val musoni_outstanding_principal : Int,
    @SerializedName("approval_type") val approval_type : String,
    @SerializedName("approval_date") val approval_date : String,
    @SerializedName("limit_activated_at") val limit_activated_at : String
)
