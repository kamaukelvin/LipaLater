package com.lipalater.androidapp.ui.auth.signup

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lipalater.androidapp.R
import com.lipalater.androidapp.ui.extension.*
import com.lipalater.androidapp.ui.widgets.SpinnerSelectListener
import com.lipalater.androidapp.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_step_2.*

class Step2Fragment : Fragment(R.layout.fragment_step_2) {
    private lateinit var viewModel: AuthViewModel
    private var employmentType = EmploymentType.PRIVATE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        setSpinnerListeners()
        populateEmploymentSpinners(R.array.employment_subtype)
        populateSpinner(employmentTypeSpinner, R.array.employment_type)
        populateSpinner(jobLevelSpinner, R.array.job_level)
        populateSpinner(jobFunctionSpinner, R.array.job_function)
        populateSpinner(jobGroupSpinner, R.array.job_group)
        populateSpinner(paymentMethodSpinner, R.array.payment_method)
        continueButton.setOnClickListener {
            if (isFormValid()) {
                saveData()
                navigateToNext()
            }
        }

    }

    private fun saveData() {
        val body = viewModel.authBody
        val isEmployed =
            employmentType == EmploymentType.GOV || employmentType == EmploymentType.PRIVATE
        body.isEmployed = isEmployed
        var employmentSector = ""
        if (isEmployed) {
            employmentSector =
                if (employmentType == EmploymentType.GOV) "government" else "private_sector"
        }
        body.employerSector = employmentSector
        when (employmentType) {
            EmploymentType.GOV -> {
                body.employer = govDeptInput.string()
                body.jobGroup = jobGroupSpinner.selectedItem as String
                body.netMonthlyIncome = govMonthlyIncomeInput.string().toDouble()
                body.netMonthlyExpenses = govMonthlyExpenseInput.string().toDouble()
            }
            EmploymentType.PRIVATE -> {
                body.employer = companyNameInput.string()
                body.jobFunction = jobFunctionSpinner.selectedItem as String
                body.jobLevel = jobLevelSpinner.selectedItem as String
                body.netMonthlyIncome = monthlyIncomeInput.string().toDouble()
                body.netMonthlyExpenses = monthlyExpenseInput.string().toDouble()
            }
            else -> {
                val businessType = if (employmentSubtypeSpinner.selectedItemPosition == 0)
                    "sole_proprietorship" else "partnership"
                body.businessType = businessType
                body.businessPaymentType = paymentMethodSpinner.selectedItem as String
                body.grossMonthlyRevenue = businessMonthlyIncomeInput.string().toDouble()
                body.averageMonthlyExpenses = businessMonthlyExpenseInput.string().toDouble()
                body.averageMonthlyPersonalExpenses =
                    personalMonthlyExpenseInput.string().toDouble()
            }
        }
    }

    private fun isFormValid(): Boolean {
        var isValid = employmentTypeSpinner.validate(message = "Please select employment type") &&
            employmentSubtypeSpinner.validate(message = "Please select employment subtype")
        isValid = isValid && when (employmentType) {
            EmploymentType.PRIVATE -> validatePrivateEmployment()
            EmploymentType.GOV -> validateGovEmployment()
            EmploymentType.BIZ_INDIVIDUAL -> validateBizIndividual()
            else -> validateBizIndividual()
        }
        return isValid
    }

    private fun validateBizIndividual(): Boolean {
        return paymentMethodSpinner.validate(0, "Please select payment method") &&
            businessMonthlyIncomeInput.validateNumber() &&
            businessMonthlyExpenseInput.validateNumber() &&
            personalMonthlyExpenseInput.validateNumber()
    }

    private fun validateGovEmployment(): Boolean {
        return govDeptInput.validateNotEmpty() &&
            jobGroupSpinner.validate(message = "Please select job group") &&
            govMonthlyIncomeInput.validateNumber() &&
            govMonthlyExpenseInput.validateNumber()
    }

    private fun validatePrivateEmployment(): Boolean {
        return companyNameInput.validateNotEmpty() &&
            jobFunctionSpinner.validate(0, message = "Please select job function") &&
            jobLevelSpinner.validate(0, message = "Please select job level") &&
            monthlyIncomeInput.validateNumber() &&
            monthlyExpenseInput.validateNumber()
    }

    private fun navigateToNext() {
        if (employmentType == EmploymentType.GOV || employmentType == EmploymentType.PRIVATE) {
            findNavController().navigate(R.id.step4Fragment)
        } else {
            findNavController().navigate(R.id.step3Fragment)
        }
    }

    private fun setSpinnerListeners() {
        employmentTypeSpinner.onItemSelectedListener = object : SpinnerSelectListener() {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val employmentSubtypeResource = if (position == 0) R.array.employment_subtype
                else R.array.self_employment_subtype
                employmentType = if (position == 0) EmploymentType.PRIVATE
                else EmploymentType.BIZ_INDIVIDUAL
                populateEmploymentSpinners(employmentSubtypeResource)
                showAppropriateSection()
            }
        }
        employmentSubtypeSpinner.onItemSelectedListener = object : SpinnerSelectListener() {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                val employmentTypePosition = employmentTypeSpinner.selectedItemPosition
                employmentType = if (employmentTypePosition == 0) {
                    if (position == 0) EmploymentType.PRIVATE
                    else EmploymentType.GOV
                } else {
                    if (position == 0) EmploymentType.BIZ_INDIVIDUAL
                    else EmploymentType.BIZ_PARTNERSHIP
                }
                showAppropriateSection()
            }
        }
    }

    private fun showAppropriateSection() {
        listOf(
            sectionGovernmentEmployment,
            sectionPrivateEmployment,
            sectionSelfEmployment
        ).forEach { it.hide() }
        when (employmentType) {
            EmploymentType.GOV -> sectionGovernmentEmployment.show()
            EmploymentType.PRIVATE -> sectionPrivateEmployment.show()
            else -> sectionSelfEmployment.show()
        }
    }

    private fun populateEmploymentSpinners(employmentSubtypeResource: Int) {
        ArrayAdapter.createFromResource(
            requireContext(),
            employmentSubtypeResource,
            R.layout.layout_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            employmentSubtypeSpinner.adapter = adapter
        }
    }
}
