package com.example.cartrack.data.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
data class UserFromApi(
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company,
    var expanded: Boolean = false
) : Parcelable

@Keep
@Parcelize
data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoLocation
) : Parcelable

@Keep
@Parcelize
data class GeoLocation(
    val lat: String,
    val lng: String
) : Parcelable

@Keep
@Parcelize
data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
) : Parcelable

