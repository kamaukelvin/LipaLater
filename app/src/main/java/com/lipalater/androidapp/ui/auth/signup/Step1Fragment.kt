package com.lipalater.androidapp.ui.auth.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lipalater.androidapp.R
import com.lipalater.androidapp.ui.extension.*
import com.lipalater.androidapp.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_step_1.*
import org.threeten.bp.LocalDate

class Step1Fragment : Fragment(R.layout.fragment_step_1) {
    private lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        populateSpinners()
        continueButton.setOnClickListener {
            if (isFormValid()) {
                saveData()
                navigateToStep2()
            }


        }

    }

    private fun saveData() {
        val body = viewModel.authBody
        body.firstName = firstNameInput.string()
        body.lastName = lastNameInput.string()
        body.phoneNumber = getPhoneNumber(countryCodePicker, phoneInput)
        body.idNumber = idNumberInput.string()
        body.dateOfBirth =
            "${getDateValue(monthSpinner.selectedItemPosition)}/${getDateValue(dayInput.string().toInt())}/${yearInput.string()}"
        body.gender = (genderSpinner.selectedItem as String).toLowerCase()
        body.maritalStatus = (maritalStatusSpinner.selectedItem as String).toLowerCase()
    }

    private fun isFormValid(): Boolean {
        return firstNameInput.validateNotEmpty() &&
            lastNameInput.validateNotEmpty() &&
            validatePhone(countryCodePicker, phoneInput) &&
            idNumberInput.validateNotEmpty() &&
            validateDate() &&
            maritalStatusSpinner.validate(0, "Please select marital status") &&
            genderSpinner.validate(0, "Please select gender")
    }

    private fun getDateValue(pos: Int): String {
        return if (pos < 10) "0$pos" else pos.toString()
    }

    private fun validateDate(): Boolean {
        var isValid = monthSpinner.validate(0, "Please select month") &&
            dayInput.validateNumber() &&
            yearInput.validateNumber()
        isValid = isValid && try {
            val dateString =
                "${yearInput.string()}-${getDateValue(monthSpinner.selectedItemPosition)}-${getDateValue(dayInput.string().toInt())}"
            LocalDate.parse(dateString)
            true
        } catch (e: Exception) {
            showMessage("Please enter a valid date", dayInput)
            false
        }
        return isValid
    }

    private fun navigateToStep2() {
        findNavController().navigate(R.id.step2Fragment)
    }

    private fun populateSpinners() {
        populateSpinner(maritalStatusSpinner, R.array.marital_status)
        populateSpinner(genderSpinner, R.array.gender)
        populateSpinner(monthSpinner, R.array.months)
    }
}
