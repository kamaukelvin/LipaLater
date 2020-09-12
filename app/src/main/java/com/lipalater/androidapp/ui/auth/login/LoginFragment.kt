package com.lipalater.androidapp.ui.auth.login

import DataLogin
import ErrorResponse
import android.content.Context
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
import com.lipalater.androidapp.ui.extension.*
import com.lipalater.androidapp.util.PrefsHelper
import com.lipalater.androidapp.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.custom_toast.view.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.layout_login.*
import timber.log.Timber


class LoginFragment: Fragment(R.layout.fragment_login) {
    private lateinit var viewModel: AuthViewModel
    private var showPassword = false
    private val passwordType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
    private val cleartextType = InputType.TYPE_CLASS_TEXT


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        setUpPasswordToggle()
        setTitleText()
        signUpButton.setOnClickListener {

            launchSignup()
        }
        resetButton.setOnClickListener {
            findNavController().navigate(R.id.resetPasswordFirstFragment)
        }
        continueLoginButton.setClickHandler{


            if (isFormValid()) {
                saveData()
                sendData()

            }

        }




    }


    private fun saveData() {
        val body = viewModel.loginBody
        body.myemail = emailLoginInput.string()
        body.mypassword = resetpasswordInput.string()
    }

    private fun sendData() {

        continueLoginButton.toggleLoading(true)
        attachToLifecycle(
            viewModel.login().subscribe(
                {

                    continueLoginButton.toggleLoading(false)
                    if(it.isSuccessful) {
                        val bodyString = it.body()?.bytes()?.let { it1 -> String(it1) }
                        val data = Gson().fromJson(bodyString, DataLogin::class.java)
                        data.data?.first_name?.let { it1 -> setUsername(it1) }
                        if(data==null){
                            Toast.makeText(activity,"Success",Toast.LENGTH_LONG)
                        }else {
                            var user = viewModel.user
                            user.first_name = data.data?.first_name
                            findNavController().navigate(R.id.profileFragment)

                        }

                    }else{

                        val bodyString = it.errorBody()?.bytes()?.let { it1 -> String(it1) }
                        val data = Gson().fromJson(bodyString, ErrorResponse::class.java)
                        createCustomToast(message =  data.errors.joinToString(), imageSrc = R.drawable.shape)
                    }

                },
                {
                    continueLoginButton.toggleLoading(false)
                    Timber.e("Signin error")
                    Timber.e(" ${it}")
                    createCustomToast(message = it.message.toString(), imageSrc = R.drawable.shape)



                }
            )
        )
    }



    fun createCustomToast(message: String, imageSrc: Int) {
        val toast = Toast(activity)
        toast.apply {

            val layout = nestedScrollview.inflate(R.layout.custom_toast)

            layout.textView.text = message
            layout.imageView.setBackgroundResource(imageSrc)
            setGravity(Gravity.BOTTOM, 0, 0)
            duration = Toast.LENGTH_SHORT
            view = layout
            show()
        }
    }

    fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
    }


    private fun setTitleText() {
        val prefs = PrefsHelper(requireContext())
        prefs.getUserName()?.let {name ->
            stepTitle.text = getString(R.string.label_welcome, name)
        } ?: run {
            stepTitle.text = getString(R.string.label_hello)
        }
    }
    private fun setUsername(s: String) {
        val prefs = PrefsHelper(requireContext())
        prefs.setUserName(s)
    }

    private fun isFormValid(): Boolean {
        return emailLoginInput.validateEmail(true) &&
            resetpasswordInput.validateNotEmpty()
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
