package com.lipalater.androidapp.api


import com.lipalater.androidapp.api.requests.AuthRequestBody
import com.lipalater.androidapp.api.requests.LoginRequestBody
import com.lipalater.androidapp.api.requests.ResetPasswordBody
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {


    @POST("/auth/")
    fun register(@Body body: AuthRequestBody): Observable<Response<ResponseBody>>

//    Sign in
    @POST("auth/sign_in")
    fun login(@Body body: LoginRequestBody):Observable<Response<ResponseBody>>

//    Reset Password
    @POST("auth/password")
    fun resetPassword(@Body body: ResetPasswordBody):Observable<Response<ResponseBody>>

}
