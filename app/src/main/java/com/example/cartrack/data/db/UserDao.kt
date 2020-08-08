package com.example.cartrack.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cartrack.data.db.model.User
import io.reactivex.Single

@Dao
interface UserDao {

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun getUser(
        username: String,
        password: String
    ): Single<User>

    @Insert
    fun insert(user: User)
}