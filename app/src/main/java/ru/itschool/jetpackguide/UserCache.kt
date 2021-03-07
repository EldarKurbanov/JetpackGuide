package ru.itschool.jetpackguide

import javax.inject.Inject

class UserCache @Inject constructor(

) {
    private val userCache: MutableMap<String, User> = mutableMapOf()

    fun put(userId : String, user : User) {
        userCache[userId] = user
    }

    fun get(userId: String) = userCache[userId]
}