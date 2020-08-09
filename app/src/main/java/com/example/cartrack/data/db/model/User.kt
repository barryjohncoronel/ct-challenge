package com.example.cartrack.data.db.model

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "user")
data class User(
    var username: String,
    var password: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
