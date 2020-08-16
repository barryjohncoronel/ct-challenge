package com.example.ct.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class GeoLocation(
    val lat: String,
    val lng: String
) : Parcelable