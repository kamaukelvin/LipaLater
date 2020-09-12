package com.lipalater.androidapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lipalater.androidapp.R

class MainActivity : AppCompatActivity() {
    companion object {
        /**
         * -1 = splash
         * 0 = auth
         * 1 = login
         * 2 = home
         * */
        var navPath = -1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        when (navPath) {
            0 -> setContentView(R.layout.layout_signup)
            1 -> setContentView(R.layout.layout_login)
            else -> run {
                setContentView(R.layout.layout_launch)
            }
        }
    }

}
