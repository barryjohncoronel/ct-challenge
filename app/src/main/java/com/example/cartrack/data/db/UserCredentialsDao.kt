package com.example.cartrack.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cartrack.data.model.UserCredentials
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface UserCredentialsDao {

    @Insert
    fun insert(userCredentials: UserCredentials): Completable

    @Query("SELECT COUNT(*) FROM user_credentials WHERE username = :username")
    fun findUsernameCount(
        username: String
    ): Single<Int>

    @Query("SELECT * FROM user_credentials WHERE username = :username AND password = :password")
    fun getUserCredentials(
        username: String,
        password: String
    ): Single<UserCredentials>

    @Query("SELECT COUNT(*) FROM user_credentials")
    fun getCount(): Single<Int>
}