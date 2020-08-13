package com.example.cartrack

import com.example.cartrack.BaseFactory.Companion.randomInt
import com.example.cartrack.BaseFactory.Companion.randomString
import com.example.cartrack.data.model.User

class UserFactory {

    companion object {
        fun users(): List<User> {
            val users = ArrayList<User>()

            for (i in 0 until 10) {
                users.add(user())
            }

            return users
        }

        fun user(): User {
            return User(
                name = randomString(),
                username = randomString(),
                email = randomString(),
                address = AddressFactory.address(),
                phone = randomString(),
                website = randomString(),
                company = CompanyFactory.company()
            )
        }
    }
}