package com.lipalater.androidapp.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.lipalater.androidapp.R
import com.lipalater.androidapp.ui.extension.asColor
import com.lipalater.androidapp.ui.extension.launchLogin
import com.lipalater.androidapp.ui.extension.launchSignup
import com.lipalater.androidapp.ui.extension.setStatusBarColor
import kotlinx.android.synthetic.main.fragment_auth.*

class AuthFragment : Fragment(R.layout.fragment_auth) {
    private lateinit var images: Array<Drawable>
    private lateinit var statusBarColors: Array<Int>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setupCarousel()
        return inflater.inflate(R.layout.fragment_auth, container, false)
    }

    private fun setupCarousel() {
        val context = requireContext()
        images = arrayOf(
            ContextCompat.getDrawable(context, R.drawable.auth_bg_1)!!,
            ContextCompat.getDrawable(context, R.drawable.auth_bg_2)!!,
            ContextCompat.getDrawable(context, R.drawable.auth_bg_3)!!
        )
        statusBarColors = arrayOf(
            R.color.statusBar1.asColor(requireContext()),
            R.color.statusBar2.asColor(requireContext()),
            R.color.statusBar3.asColor(requireContext())
        )
        changeStatusBarColor(statusBarColors[0])
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        startCarouselAnimation()
        signUpButton.setOnClickListener {
            launchSignup()
        }

        loginButton.setOnClickListener { launchLogin() }
    }



    private fun startCarouselAnimation() {
        indicator.beforeAnimate { position ->
            backgroundImage.setImageDrawable(images[position])
            changeStatusBarColor(statusBarColors[position])
        }
        indicator.setLifecycleOwner(this)
        indicator.animateProgress()
    }

    private fun changeStatusBarColor(color: Int) {
        requireActivity().setStatusBarColor(color)
    }

    override fun onPause() {
        changeStatusBarColor(R.color.statusBarGrey.asColor(requireContext()))
        super.onPause()
    }
}
