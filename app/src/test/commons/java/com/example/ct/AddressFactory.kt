package com.example.ct

import com.example.ct.BaseFactory.Companion.randomString
import com.example.ct.data.model.Address

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