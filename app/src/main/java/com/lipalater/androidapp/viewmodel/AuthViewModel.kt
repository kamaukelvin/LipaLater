package com.lipalater.androidapp.viewmodel

import androidx.lifecycle.ViewModel
import com.lipalater.androidapp.api.requests.AuthRequestBody
import com.lipalater.androidapp.api.requests.LoginRequestBody
import com.lipalater.androidapp.api.requests.ResetPasswordBody
import com.lipalater.androidapp.api.requests.UserBody
import com.lipalater.androidapp.repository.AuthRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import retrofit2.Response

class AuthViewModel: ViewModel() {
    //set data  authbody from fragments  ui
    val authBody: AuthRequestBody = AuthRequestBody()
    val loginBody: LoginRequestBody = LoginRequestBody()
    val resetBody: ResetPasswordBody = ResetPasswordBody()


    val user: UserBody =UserBody()

    fun register(): Observable<Response<ResponseBody>> {
        val repo = AuthRepository()
        return repo.register(authBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun login():  Observable<Response<ResponseBody>>{
        val repo = AuthRepository()
        return repo.login(loginBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun resetPassword(): Observable<Response<ResponseBody>>? {
        val repo = AuthRepository()
        return repo.resetPassword(resetBody)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
