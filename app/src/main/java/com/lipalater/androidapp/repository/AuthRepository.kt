package com.lipalater.androidapp.repository

import com.lipalater.androidapp.api.ApiServiceWrapper
import com.lipalater.androidapp.api.requests.AuthRequestBody
import com.lipalater.androidapp.api.requests.LoginRequestBody
import com.lipalater.androidapp.api.requests.ResetPasswordBody


import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response

class AuthRepository {
    fun register(data: AuthRequestBody): Observable<Response<ResponseBody>> {
        val apiService = ApiServiceWrapper.getApiService()
        return apiService.register(data)
    }

//    sign in

    fun login(data: LoginRequestBody): Observable<Response<ResponseBody>> {
        val apiService = ApiServiceWrapper.getApiService()
        return apiService.login(data)
    }

//    reset password
    fun resetPassword(data: ResetPasswordBody): Observable<Response<ResponseBody>> {
        val apiService = ApiServiceWrapper.getApiService()
        return apiService.resetPassword(data)
}

}
