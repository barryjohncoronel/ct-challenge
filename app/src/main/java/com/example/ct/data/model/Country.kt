package com.example.ct.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class Country(
    val code: String,
    val name: String,
    var selected: Boolean = false
) : Parcelable