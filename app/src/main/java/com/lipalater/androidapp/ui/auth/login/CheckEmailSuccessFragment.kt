package com.lipalater.androidapp.ui.auth.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lipalater.androidapp.R
import kotlinx.android.synthetic.main.fragment_checkemail_success.*
import kotlinx.android.synthetic.main.fragment_reset_password.*
import kotlinx.android.synthetic.main.fragment_reset_password.resetpasswordButton

class CheckEmailSuccessFragment:Fragment(R.layout.fragment_checkemail_success) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        buttonResetSuccess.setClickHandler {
            findNavController().navigate(R.id.resetPasswordFragment)

        }




    }

}
