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
    private val _user = MutableLiveData<User>()
    val user : LiveData<User> = _user

    init {
        viewModelScope.launch {
            _user.value = userRepository.getUser(userId)
        }
    }
}