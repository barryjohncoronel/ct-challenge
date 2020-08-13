package com.example.cartrack

import com.example.cartrack.BaseFactory.Companion.randomString
import com.example.cartrack.data.model.Address
import com.example.cartrack.data.model.Company

class CompanyFactory {

    companion object {
        fun company(): Company {
            return Company(
                name = randomString(),
                catchPhrase = randomString(),
                bs = randomString()
            )
        }
    }
}