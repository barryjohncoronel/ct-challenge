package com.example.cartrack.data.network.user

import androidx.annotation.Keep

@Keep
data class UserFromApi(
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
) {
    var expanded: Boolean = false
}

@Keep
data class Address(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    val geo: GeoLocation
)

@Keep
data class GeoLocation(
    val lat: String,
    val long: String
)

@Keep
data class Company(
    val name: String,
    val catchPhrase: String,
    val bs: String
)

