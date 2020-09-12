package com.lipalater.androidapp.api.requests

import com.google.gson.annotations.SerializedName
import com.lipalater.androidapp.util.*



class AuthRequestBody {
    @SerializedName(EMAIL)
    var email: String? = null
    @SerializedName(PASSWORD)
    var password: String? = null
    @SerializedName(PASSWORD_CONFIRMATION)
    var passwordConfirmation: String? = null
    @SerializedName(FIRST_NAME)
    var firstName: String? = null
    @SerializedName(LAST_NAME)
    var lastName: String? = null
    @SerializedName(ID_NUMBER)
    var idNumber: String? = null
    @SerializedName(PHONE_NUMBER)
    var phoneNumber: String? = null
    @SerializedName(MARITAL_STATUS)
    var maritalStatus: String? = null
    @SerializedName(GENDER)
    var gender: String? = null
    @SerializedName(IS_EMPLOYED)
    var isEmployed: Boolean? = null
    @SerializedName(EMPLOYER)
    var employer: String? = null
    @SerializedName(NET_MONTHLY_INCOME)
    var netMonthlyIncome: Double? = null
    @SerializedName(NET_MONTHLY_EXPENSES)
    var netMonthlyExpenses: Double? = null
    @SerializedName(JOB_FUNCTION)
    var jobFunction: String? = null
    @SerializedName(JOB_LEVEL)
    var jobLevel: String? = null
    @SerializedName(EMPLOYER_SECTOR)
    var employerSector: String? = null
    @SerializedName(JOB_GROUP)
    var jobGroup: String? = null
    @SerializedName(PAYMENT_TYPE)
    var paymentType: String? = null
    @SerializedName(DATE_OF_BIRTH)
    var dateOfBirth: String? = null
    @SerializedName(BUSINESS_TYPE)
    var businessType: String? = null
    @SerializedName(BUSINESS_NAME)
    var businessName: String? = null
    @SerializedName(BUSINESS_LOCATION)
    var businessLocation: String? = null
    @SerializedName(BUSINESS_PAYMENT_TYPE)
    var businessPaymentType: String? = null
    @SerializedName(GROSS_MONTHLY_REVENUE)
    var grossMonthlyRevenue: Double? = null
    @SerializedName(AVERAGE_MONTHLY_EXPENSES)
    var averageMonthlyExpenses: Double? = null
    @SerializedName(AVERAGE_MONTHLY_PERSONAL_EXPENSE)
    var averageMonthlyPersonalExpenses: Double? = null
}
