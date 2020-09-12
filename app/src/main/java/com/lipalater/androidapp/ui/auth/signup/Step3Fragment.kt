package com.lipalater.androidapp.ui.auth.signup

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lipalater.androidapp.R
import com.lipalater.androidapp.ui.extension.*
import com.lipalater.androidapp.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_step_3.*

class Step3Fragment : Fragment(R.layout.fragment_step_3) {
    private lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        populateSpinner(bizLocationSpinner, R.array.biz_location)
        continueButton.setOnClickListener {
            if (isFormValid()) {
                saveData()
                navigateToNext()
            }
        }

    }

    private fun saveData() {
        val body = viewModel.authBody
        val businessLocation =
            "${officeSuiteInput.string()} ${streetNameInput.string()} " +
                "${bizLocationSpinner.selectedItem as String} "
        body.businessLocation = businessLocation
    }

    private fun isFormValid(): Boolean {
        return bizLocationSpinner.validate(0, "Please select business location") &&
            officeSuiteInput.validateNotEmpty() &&
            streetNameInput.validateNotEmpty() &&
            validatePhone(countryCodePicker, phoneInput) &&
            businessEmailInput.validateEmail()
    }

    private fun navigateToNext() {
        findNavController().navigate(R.id.step4Fragment)
    }
}
