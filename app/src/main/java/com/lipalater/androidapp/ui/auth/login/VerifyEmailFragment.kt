package com.lipalater.androidapp.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lipalater.androidapp.R
import kotlinx.android.synthetic.main.fragment_verify_email.*


class VerifyEmailFragment:Fragment(R.layout.fragment_verify_email) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        openemailButton.setOnClickListener {
            val mailClient = Intent(Intent.ACTION_VIEW)
            mailClient.setClassName(
                "com.google.android.gm",
                "com.google.android.gm.ConversationListActivity"
            )
            startActivity(mailClient)

        }


        cancelImageView.setOnClickListener {
            findNavController().navigate(R.id.resetPasswordFirstFragment)
        }




        }



    }
