package ru.itschool.jetpackguide

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.DefineComponent
import dagger.hilt.InstallIn
import kotlin.coroutines.coroutineContext

@DefineComponent
class ViewModelProviderFactory(private val args : SavedStateHandle, private val context : Context) : ViewModelProvider.Factory {

    @Provides
    private fun userRepository() : UserRepository {
        return UserRepository(WebServiceDummy(), Room.databaseBuilder(context, UserDatabase::class.java, "UserDB").build().userDao())
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (UserProfileViewModel::class.java.isAssignableFrom(modelClass)) {
            @Suppress("UNCHECKED_CAST")
            return UserProfileViewModel(args, userRepository()) as T
        } else
            throw InstantiationException("ViewModelProviderFactory doesn't know about this ViewModel class")
    }

}