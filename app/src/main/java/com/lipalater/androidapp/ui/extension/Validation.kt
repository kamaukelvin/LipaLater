package com.lipalater.androidapp.ui.extension

import android.util.Patterns
import android.widget.EditText
import android.widget.Spinner
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.hbb20.CountryCodePicker

fun EditText.validateNotEmpty(error: String? = null): Boolean {
    val message = error ?: "This field is required."
    val isValid = string().isNotEmpty()
    showError(isValid, message)
    return isValid
}

fun EditText.validateNumber(): Boolean {
    val isValid = Regex("\\d+").matches(this.string())
    showError(isValid, "Enter a valid number")
    return isValid
}

fun EditText.validateEmail(emailRequired: Boolean = false): Boolean {
    if (!emailRequired && this.string().isEmpty()) return true
    val isValid = Patterns.EMAIL_ADDRESS.matcher(this.string()).matches()
    showError(isValid, "Enter a valid email address")
    return isValid
}

fun EditText.showError(isValid: Boolean, error: String) {
    if (!isValid) {
        this.error = error
    } else {
        this.error = null
    }
}

fun Spinner.validate(minIndex: Int = -1, message: String): Boolean {
    val isValid = this.selectedItemPosition > minIndex
    if (!isValid) {
        showMessage(message)
    }
    return isValid
}

fun validatePhone(ccp: CountryCodePicker, input: EditText): Boolean {
    val phoneUtil = PhoneNumberUtil.getInstance()
    val isValidNumber = try {
        val phoneNumberObj = phoneUtil.parse(input.string(), ccp.selectedCountryNameCode)
        phoneUtil.isValidNumber(phoneNumberObj)
    } catch (e: Exception) {
        false
    }
    val message = "Enter a valid phone number"
    input.showError(isValidNumber, message)
    return isValidNumber
}
