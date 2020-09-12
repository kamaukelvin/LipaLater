package com.lipalater.androidapp.ui.auth.login

import android.os.Bundle
import android.view.View
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.ViewModelProvider
import com.lipalater.androidapp.R
import kotlinx.android.synthetic.main.fragment_resetpassword_first.*
import com.lipalater.androidapp.ui.extension.*
import com.lipalater.androidapp.viewmodel.AuthViewModel
import timber.log.Timber




class ResetPasswordFirstFragment: Fragment(R.layout.fragment_resetpassword_first) {
    private lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)

        resetemailContinueButton.setClickHandler {
            findNavController().navigate(R.id.resetPasswordFragment)

//            findNavController().navigate(R.id.checkEmailSuccessFragment)
        }

        resetemailContinueButton.setClickHandler {
            if (isFormValid()) {
                saveData()
                sendData()
            }


        }

    }
        private fun saveData() {
            val body = viewModel.resetBody
            body.email = emailInput.string()
        }

        private fun sendData() {

            resetemailContinueButton.toggleLoading(true)
            viewModel.resetPassword()?.subscribe(
                {
                    resetemailContinueButton.toggleLoading(false);


//                    if(it.isSuccessful){
                        val bodyString = it.body()?.bytes()?.let { it1 -> String(it1) }

//                    val topic = Gson().fromJson(bodyString, LoginResponseBody::class.java)
                        Timber.e(" ${bodyString}")
//                        var gson = Gson()
//                        var userDetails = gson?.fromJson(bodyString, LoginResponseBody::class.java)
//                        Timber.e("Sign in successful")
//                        Timber.e(" ${userDetails}")
//                    }
                },
                {
                    resetemailContinueButton.toggleLoading(false)
                    Log.d("reset",it.toString())


                }
            )?.let {
                attachToLifecycle(
                    it
                )
            }
        }
        private fun isFormValid(): Boolean {
            return emailInput.validateEmail(true)
        }
    }

