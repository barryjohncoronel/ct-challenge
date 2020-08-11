package com.example.cartrack.util

import android.view.View
import android.view.View.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.example.cartrack.R

@BindingAdapter("invisibleUnless")
fun View.setInvisibleUnless(isVisible: Boolean) {
    visibility = if (isVisible) VISIBLE else INVISIBLE
}

@BindingAdapter("goneUnless")
fun View.setGoneUnless(isVisible: Boolean) {
    visibility = if (isVisible) VISIBLE else GONE
}

@BindingAdapter("isSelected")
fun TextView.setSelectedBackground(isSelected: Boolean) {
    background = if (isSelected) {
        ContextCompat.getDrawable(context, R.color.colorSelectedGray)
    } else {
        ContextCompat.getDrawable(context, android.R.color.transparent)
    }
}

