package com.example.cartrack.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cartrack.data.model.User
import io.reactivex.Completable
import io.reactivex.Single


@Dao
interface UserDao {

    @Insert
    fun insert(user: User): Completable

    @Query("SELECT COUNT(*) FROM user WHERE username = :username")
    fun findUsernameCount(
        username: String
    ): Single<Int>

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    fun getUser(
        username: String,
        password: String
    ): Single<User>

    @Query("SELECT COUNT(*) FROM user")
    fun getCount(): Single<Int>
}