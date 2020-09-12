package com.lipalater.androidapp.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.lipalater.androidapp.R
import kotlinx.android.synthetic.main.layout_input_border.view.*

class InputBorder @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var view: View =
        LayoutInflater.from(context).inflate(R.layout.layout_input_border, this)

    override fun addView(child: View?) {
        child?.let {
            content.addView(it)
        } ?: super.addView(child)
    }
}
