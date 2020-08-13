package com.example.cartrack.data.service.users

import com.example.cartrack.data.model.User
import io.reactivex.Single

interface UsersService {

    fun getUsers(): Single<List<User>>
}