package com.lipalater.androidapp.ui.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.hbb20.CountryCodePicker
import com.lipalater.androidapp.R
import com.lipalater.androidapp.ui.MainActivity
import io.reactivex.disposables.Disposable
import timber.log.Timber

fun Activity.setStatusBarColor(color: Int) {
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = color
}

fun Int.asColor(context: Context) = ContextCompat.getColor(context, this)


fun Fragment.attachToLifecycle(disposable: Disposable) =
    this.lifecycle.addObserver(LifecycleDisposable(disposable))

fun Fragment.populateSpinner(spinner: Spinner, resId: Int) {
    ArrayAdapter.createFromResource(
        requireContext(),
        resId,
        R.layout.layout_spinner_item
    ).also { adapter ->
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}

fun Fragment.launchLogin() {
    MainActivity.navPath = 1
    Intent(requireActivity(), MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        requireActivity().startActivity(this)
    }
}

fun Fragment.launchSignup() {
    MainActivity.navPath = 0
    Intent(requireActivity(), MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        requireActivity().startActivity(this)
    }
}

fun Fragment.showMessage(message: String, contextView: View) {
    Snackbar.make(contextView, message, Snackbar.LENGTH_SHORT).show()
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.showMessage(message: String) {
    Snackbar.make(this, message, Snackbar.LENGTH_SHORT).show()
}

fun EditText.string(): String = this.text.toString()

fun getPhoneNumber(ccp: CountryCodePicker, input: EditText): String {
    var phoneNumber = ""
    val phoneUtil = PhoneNumberUtil.getInstance()
    try {
        val phoneNumberObj = phoneUtil.parse(input.string(), ccp.selectedCountryNameCode)
        phoneNumber = "+${phoneNumberObj.countryCode}${phoneNumberObj.nationalNumber}"
    } catch (e: Exception) {
        Timber.d("Parsing phone number failed: ${e.message}")
    }
    return phoneNumber
}
