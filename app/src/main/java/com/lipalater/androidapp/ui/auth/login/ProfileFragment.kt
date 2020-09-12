package com.lipalater.androidapp.ui.auth.login

import DataLogin
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.lipalater.androidapp.R
import com.lipalater.androidapp.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.frament_profile.*

class ProfileFragment:Fragment(R.layout.frament_profile) {
    private lateinit var viewModel: AuthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)

        getUserDetails();
        imgageViewcancel.setOnClickListener {
            findNavController().navigate(R.id.loginFragment)
        }




    }
    private fun getUserDetails(){
        val body = viewModel.user
        textViewname.text=(body.first_name )

    }

}
