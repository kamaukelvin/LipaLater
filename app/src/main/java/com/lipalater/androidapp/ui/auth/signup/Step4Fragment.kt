package com.lipalater.androidapp.ui.auth.signup

import DataRegister
import ErrorResponse
import ErrorResponseRegister
import RegisterUser
import ResponseErrorRegister
import ResponseRegisterNew
import android.graphics.Typeface
import android.os.Bundle
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.lipalater.androidapp.R
import com.lipalater.androidapp.ui.extension.attachToLifecycle
import com.lipalater.androidapp.ui.extension.string
import com.lipalater.androidapp.ui.extension.validateEmail
import com.lipalater.androidapp.ui.extension.validateNotEmpty
import com.lipalater.androidapp.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_step_4.*
import kotlinx.android.synthetic.main.fragment_step_4.resetpasswordInput
import kotlinx.android.synthetic.main.fragment_step_4.showPasswordButton

class Step4Fragment : Fragment(R.layout.fragment_step_4) {
    private lateinit var viewModel: AuthViewModel
    private var showPassword = false
    private val passwordType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    private val cleartextType = InputType.TYPE_CLASS_TEXT

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        setUpPasswordToggle()
        continueButton.setClickHandler {
            if (isFormValid()) {
                saveFragment4Data()
                sendData()


            }
        }
    }

    private fun saveFragment4Data() {
        val body = viewModel.authBody
        body.email = emailInput.string()
        body.password = resetpasswordInput.string()
        body.passwordConfirmation = repeatPasswordInput.string()
    }

    private fun isFormValid(): Boolean {
        return emailInput.validateEmail(true) &&
            resetpasswordInput.validateNotEmpty() &&
            repeatPasswordInput.validateNotEmpty() &&
            resetpasswordInput.string() == repeatPasswordInput.string()
    }

    private fun sendData() {
        continueButton.toggleLoading(true)
        attachToLifecycle(
            viewModel.register().subscribe(
                {
                    continueButton.toggleLoading(false)
                    if(it.isSuccessful) {
                        val bodyString = it.body()?.bytes()?.let { it1 -> String(it1) }
                        val data = Gson().fromJson(bodyString, ResponseRegisterNew::class.java)
                        findNavController().navigate(R.id.verifyEmailFragment2)

                    }
                    else{

                        val bodyString = it.errorBody()?.bytes()?.let { it1 -> String(it1) }
                        val data = Gson().fromJson(bodyString, ErrorResponseRegister::class.java)
                        createCustomToast(message =  data.errors.joinToString(), imageSrc = R.drawable.shape)
                    }




                },
                {
                    continueButton.toggleLoading(false)
//                    view.displayError(getLoginErrorMessage(it))
                    Toast.makeText(activity," ${it.message}", Toast.LENGTH_LONG).show()


                }
            )
        )
    }

    fun createCustomToast(message: String, imageSrc: Int) {
        val toast = Toast(activity)
        toast.apply {

            val layout = nestedScrollview2.inflate(R.layout.custom_toast)

            layout.textView.text = message
            layout.imageView.setBackgroundResource(imageSrc)
            setGravity(Gravity.BOTTOM, 0, 0)
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }

    private fun setUpPasswordToggle() {
        showPasswordButton.setOnClickListener {
            if (resetpasswordInput.text.isNotEmpty()) {
                showPassword = !showPassword
                resetpasswordInput.inputType = if (showPassword) passwordType else cleartextType
                resetpasswordInput.setSelection(resetpasswordInput.text.length)
                resetpasswordInput.typeface = Typeface.DEFAULT
                showPasswordButton.text =
                    getString(if (showPassword) R.string.label_hide else R.string.label_show)
            }
        }
    }
}
