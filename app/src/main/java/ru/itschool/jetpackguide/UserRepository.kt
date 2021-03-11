package ru.itschool.jetpackguide

import android.accounts.NetworkErrorException
import dagger.Provides
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val webService : WebServiceDummy,
//    private val userCache : UserCache
    //private val executor : Executor,
    private val userDao: UserDao
) {
    suspend fun getUser(userId : String) : Flow<User> {
        refreshUser(userId)
        return userDao.load(userId)
//        val cached : User? = userCache.get(userID)
//        if (cached != null) return cached
//        val freshUser = webService.getUser(userID)
//        if (freshUser != null) {
//            userCache.put(userID, freshUser)
//            return freshUser
//        }
//        throw NetworkErrorException("User from server is null")
    }

    @Provides
    private suspend fun refreshUser(userId : String) {
        val userExists = userDao.hasUser(FRESH_TIMEOUT, userId)
        if (!userExists) {
            val response = webService.getUser(userId)
                ?: throw NetworkErrorException("User from webservice is null")
            userDao.save(response)
        }
    }

    companion object {
        val FRESH_TIMEOUT = TimeUnit.DAYS.toMillis(1)
    }
}