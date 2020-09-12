package com.lipalater.androidapp.ui.widgets

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.lipalater.androidapp.R
import kotlinx.android.synthetic.main.layout_button_progress.view.*

class ProgressButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private var view: View =
        LayoutInflater.from(context).inflate(R.layout.layout_button_progress, this)
    private var buttonText: String
    private var clickHandler: (() -> Unit)? = null
    private var clickListener: OnClickListener

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ProgressButton, defStyleAttr, 0)
        try {
            val resId = typedArray.getResourceId(R.styleable.ProgressButton_btnText, R.string.empty)
            buttonText = context.getString(resId)
            button.text = buttonText
        } finally {
            typedArray.recycle()
        }
        clickListener = OnClickListener {
            clickHandler?.let { it() }
        }
        button.setOnClickListener(clickListener)
    }

    fun setClickHandler(handler: () -> Unit) {
        clickHandler = handler
    }

    fun toggleLoading(isLoading: Boolean) {
        val visibility = if (isLoading) VISIBLE else GONE
        val text = if(isLoading) "" else buttonText
        button.text = text
        button.setOnClickListener(if (isLoading) null else clickListener)
        progress.visibility = visibility
    }
}
