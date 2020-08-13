package com.example.cartrack

import com.example.cartrack.BaseFactory.Companion.randomString
import com.example.cartrack.data.model.Address

class AddressFactory {

    companion object {
        fun address(): Address {
            return Address(
                street = randomString(),
                suite = randomString(),
                city = randomString(),
                zipcode = randomString(),
                geo = GeoLocationFactory.geoLocation()
            )
        }
    }
}