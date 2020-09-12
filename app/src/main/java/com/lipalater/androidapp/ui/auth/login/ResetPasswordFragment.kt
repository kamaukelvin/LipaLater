package com.lipalater.androidapp.ui.auth.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lipalater.androidapp.R
import kotlinx.android.synthetic.main.fragment_reset_password.*


class ResetPasswordFragment:Fragment(R.layout.fragment_reset_password) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        resetpasswordButton.setOnClickListener {
            findNavController().navigate(R.id.verifyEmailFragment)
        }





    }


}
