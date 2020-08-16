package com.example.ct

import com.example.ct.BaseFactory.Companion.randomString
import com.example.ct.data.model.Company

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