package com.example.cartrack.data.service.users

import com.example.cartrack.data.model.UserFromApi
import io.reactivex.Single

interface UsersService {

    fun getUsers(): Single<List<UserFromApi>>
}