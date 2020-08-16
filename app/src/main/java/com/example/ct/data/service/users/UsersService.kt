package com.example.ct.data.service.users

import com.example.ct.data.model.User
import io.reactivex.Single

interface UsersService {

    fun getUsers(): Single<List<User>>
}