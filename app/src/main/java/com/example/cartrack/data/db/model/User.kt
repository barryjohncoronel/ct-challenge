package com.example.cartrack.data.db.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@Entity(tableName = "user")
data class User(

    @PrimaryKey(autoGenerate = true)
    var id: Long? = 0,

    var username: String,

    var password: String
)
