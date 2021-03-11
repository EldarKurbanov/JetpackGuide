package ru.itschool.jetpackguide

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withTimeoutOrNull

@Dao
interface UserDao {
    @Insert(onConflict = REPLACE)
    fun save(user : User)

    @Query("SELECT * FROM user WHERE id = :userId")
    fun load(userId : String) : Flow<User>

    suspend fun hasUser(timeout : Long, userId: String) =
        withTimeoutOrNull(timeout) {
            load(userId)
        } != null
}