package com.lipalater.androidapp.ui.widgets

import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.animation.addListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.lipalater.androidapp.R
import kotlinx.android.synthetic.main.layout_indicators.view.*


class Indicator @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var view: View = LayoutInflater.from(context).inflate(R.layout.layout_indicators, this)
    private var currentPosition = 0
    private var indicators: Array<ProgressBar> = arrayOf(indicator0, indicator1, indicator2)
    private var beforeAnimate: ((Int) -> Unit)? = null
    private lateinit var lifecycleOwner: LifecycleOwner
    private val animations = mutableListOf<ObjectAnimator>()

    init {
        resetIndicators()
        handleClicks()
    }

    private fun handleClicks() {
        indicator0.setOnClickListener {
            onIndicatorClick(0)
        }
        indicator1.setOnClickListener {
            onIndicatorClick(1)
        }
        indicator2.setOnClickListener {
            onIndicatorClick(2)
        }
    }

    private fun onIndicatorClick(position: Int) {
        animations.forEach { it.pause() }
        for (i in indicators.indices) {
            if (i > position) {
                indicators[i].progress = 0
            } else {
                indicators[i].progress = 100
            }
        }
        currentPosition = position
        animateProgress()
    }

    fun beforeAnimate(func: (Int) -> Unit) {
        this.beforeAnimate = func
    }

    private fun resetIndicators() {
        indicators.forEach {
            it.progress = 0
        }
    }

    fun setLifecycleOwner(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }

    fun animateProgress() {
        val indicator = indicators[currentPosition]
        ObjectAnimator.ofInt(indicator, "progress", 0, 100)
            .also { animator ->
                animator.duration = 4000
                animator.interpolator = LinearInterpolator()
                animator.addListener(
                    onEnd = {
                        currentPosition += 1
                        if (currentPosition > 2) {
                            currentPosition = 0
                            resetIndicators()
                        }
                        animateProgress()
                    }
                )
                animations.add(animator)
                animator.start()
            }
        beforeAnimate?.let {
            if (lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                beforeAnimate!!(currentPosition)
            } else {
                animations.clear()
            }
        }
    }
}
