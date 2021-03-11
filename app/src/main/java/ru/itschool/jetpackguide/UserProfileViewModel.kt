package ru.itschool.jetpackguide

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    userRepository: UserRepository
) : ViewModel() {
    var userId: String = savedStateHandle["uid"]
            ?: throw IllegalArgumentException("Missing user ID")
    lateinit var user : LiveData<User>
    init {
        viewModelScope.launch {
            user = userRepository.getUser(userId).asLiveData()
        }
    }
}