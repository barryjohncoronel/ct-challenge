package com.example.ct.ui.users.adapter

import com.example.ct.data.model.Address
import com.example.ct.data.model.User

class UserItemViewModel(user: User) {

    val name = user.name

    val username = user.username

    val email = user.email

    val address = setAddress(user.address)

    val company = user.company.name

    val expanded = user.expanded

    private fun setAddress(address: Address): String {
        address.apply {
            return "${suite}, ${street}" +
                    "\n${city}, ${zipcode}"
        }
    }
}