package com.lipalater.androidapp.api.responses.models

data class User (
    var id: String?,
    var email :String?,
    var provider: String?,
    var countries_id: String?,
    var date_of_birth:String?,
    var first_name: String?,
    var last_name: String?,
    var phone_number :String?,
    var id_number: String?,
    var uid: String?,
    var allow_password_change:Boolean?,
    var name: String?,
    var nickname: String?,
    var image: String?,
    var marital_status: String?,
    var gender: String?,
    var employed: Boolean?,
    var musoni_id: Int?,
    var customer_standing:String?,
    var status: Boolean?,
    var deleted_at: String?,
    var cold_call:Boolean?,
    var referral_source: String?,
    var assignee: String?
)
