package ru.itschool.jetpackguide

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val id : String,
    val name: String,
    val age: Int
)
