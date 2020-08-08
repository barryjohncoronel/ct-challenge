package com.example.cartrack.util

import android.view.View
import android.view.View.*
import androidx.databinding.BindingAdapter

@BindingAdapter("bind:invisibleUnless")
fun View.setInvisibleUnless(isVisible: Boolean) {
    visibility = if (isVisible) VISIBLE else INVISIBLE
}

@BindingAdapter("bind:goneUnless")
fun View.setGoneUnless(isVisible: Boolean) {
    visibility = if (isVisible) VISIBLE else GONE
}