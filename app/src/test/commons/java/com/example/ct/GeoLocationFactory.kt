package com.example.ct

import com.example.ct.BaseFactory.Companion.randomString
import com.example.ct.data.model.GeoLocation

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