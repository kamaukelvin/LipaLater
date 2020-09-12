package com.lipalater.androidapp

import android.app.Application
import androidx.room.Room
import com.jakewharton.threetenabp.AndroidThreeTen
import io.reactivex.plugins.RxJavaPlugins
import org.koin.androidx.viewmodel.BuildConfig
import timber.log.Timber

class App : Application() {
    companion object {
        lateinit var INSTANCE: App
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        initJsr310()
//        if (BuildConfig.DEBUG) {
//            setUpDebugConfig()
//        } else {
//            initRxErrorHandling()
//        }
        setUpDebugConfig()
        initRxErrorHandling()
    }

    private fun initJsr310() {
        AndroidThreeTen.init(this)
    }

    private fun setUpDebugConfig() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initRxErrorHandling() {
        RxJavaPlugins.setErrorHandler { error: Throwable ->
            Timber.d(
                "Undeliverable exception received: %s",
                error.message
            )
        }
    }
}
