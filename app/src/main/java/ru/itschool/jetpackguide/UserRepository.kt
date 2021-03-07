package ru.itschool.jetpackguide

import android.accounts.NetworkErrorException
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val webService : WebServiceDummy,
    private val userCache : UserCache
) {
    suspend fun getUser(userID : String) : User {
        val cached : User? = userCache.get(userID)
        if (cached != null) return cached
        val freshUser = webService.getUser(userID)
        if (freshUser != null) {
            userCache.put(userID, freshUser)
            return freshUser
        }
        throw NetworkErrorException("User from server is null")
    }
}