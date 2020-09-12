package com.lipalater.androidapp.api

import android.content.Context
import com.lipalater.androidapp.util.PrefsHelper
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.io.IOException
import java.util.concurrent.TimeUnit

object ApiServiceWrapper {
    fun getApiService(): ApiService {
        val logger = HttpLoggingInterceptor.Logger { message ->
            Timber.tag("OkHttp").d(message)
        }
        val loggingInterceptor = HttpLoggingInterceptor(logger)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
            .addInterceptor(getTokenAndHeaderInterceptor())
            .addInterceptor(ReceivedCookiesInterceptor())
            .connectTimeout(100, TimeUnit.SECONDS)
            .callTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
        builder.addInterceptor(loggingInterceptor)

        val httpClient = builder.build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://staging-core-v2-backend.herokuapp.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
        return retrofit.create(ApiService::class.java)
    }

    private fun getTokenAndHeaderInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            val token = ""
            Timber.d("Auth token: %s", token)
            val originalRequest = chain.request()
            val builder = originalRequest.newBuilder()
                /*.header("Content-Type", "application/json")
                .header("WWW-Authenticate", "Token")*/
            if (token.isNotEmpty()) {
                builder.header("Authorization", "Token $token")
            }
            val request = builder.build()
            chain.proceed(request)
        }
    }

    class ReceivedCookiesInterceptor : Interceptor {

    private var context: Context? = null
    fun ReceivedCookiesInterceptor(context: Context?) {
        this.context = context
    }
        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val originalResponse: Response = chain.proceed(chain.request())
            if (!originalResponse.headers("Set-Cookie").isEmpty()) {
                val cookies: HashSet<String> = HashSet()
                for (header in originalResponse.headers("Set-Cookie")) {
                    Timber.e(header)
                    cookies.add(header)
                }



//                Timber.e(cookies.toString())
//                var prefsHelper: PrefsHelper? = context?.let { PrefsHelper(it) };
//                prefsHelper?.setToken(cookies.toString())
            }
            return originalResponse
        }
    }
}
