package com.pkmn.app

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pkmn.app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _logoutState = MutableStateFlow<Boolean>(false)
    val logoutState: StateFlow<Boolean> = _logoutState

    fun logOut() {
        viewModelScope.launch {
            try {
                userRepository.clearUserSession()
                _logoutState.value = true
            } catch (e: Exception) {
                e.printStackTrace()
                _logoutState.value = false
            }
        }
    }

}
