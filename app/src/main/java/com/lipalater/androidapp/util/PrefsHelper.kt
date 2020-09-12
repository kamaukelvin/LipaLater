package com.lipalater.androidapp.util

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

const val USER_NAME = "user_name"
const val TOKEN = "token"

class PrefsHelper(context: Context) {
    private val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)

    fun getUserName(): String? = prefs.getString(USER_NAME, null)
    fun getToken(): String? = prefs.getString(USER_NAME, null)

    fun setUserName(name: String) = putString(USER_NAME, name)
    fun setToken(name: String) = putString(TOKEN, name)

    private fun putBoolean(key: String) {
        val editor = prefs.edit()
        editor.putBoolean(key, true)
        editor.apply()
    }

    private fun putString(key: String, value: String?) {
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    private fun putInt(key: String, value: Int) {
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    private fun putLong(key: String, value: Long) {
        val editor = prefs.edit()
        editor.putLong(key, value)
        editor.apply()
    }

    fun setToken() {
        TODO("Not yet implemented")
    }
}
