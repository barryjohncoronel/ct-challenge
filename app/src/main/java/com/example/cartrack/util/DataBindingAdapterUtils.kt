package com.example.cartrack.util

import android.view.View
import android.view.View.*
import androidx.databinding.BindingAdapter

@BindingAdapter("invisibleUnless")
fun View.setInvisibleUnless(isVisible: Boolean) {
    visibility = if (isVisible) VISIBLE else INVISIBLE
}

@BindingAdapter("goneUnless")
fun View.setGoneUnless(isVisible: Boolean) {
    visibility = if (isVisible) VISIBLE else GONE
}