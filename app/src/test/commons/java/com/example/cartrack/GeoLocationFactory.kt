package com.example.cartrack

import com.example.cartrack.BaseFactory.Companion.randomString
import com.example.cartrack.data.model.Address
import com.example.cartrack.data.model.GeoLocation

class GeoLocationFactory {

    companion object {
        fun geoLocation(): GeoLocation {
            return GeoLocation(
                lat = randomString(),
                lng = randomString()
            )
        }
    }
}