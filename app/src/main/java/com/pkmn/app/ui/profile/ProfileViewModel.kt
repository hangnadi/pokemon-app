package com.pkmn.app.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pkmn.app.domain.model.UserProfile
import com.pkmn.app.domain.repository.UserRepository
import com.pkmn.app.ui.component.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    var uiState = MutableStateFlow<UiState>(UiState.Idle)
        private set
    private val _userProfile = MutableStateFlow<UserProfile>(UserProfile("", ""))
    val userProfile = _userProfile.asStateFlow()

    init {
        uiState.value = UiState.Idle
        loadProfile()
    }

    private fun loadProfile() {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            try {
                val email = repository.getUserSession()
                email?.let { if (it.isEmpty()) return@launch }

                val result = repository.getUserByEmail(email!!)
                if (result != null) {
                    uiState.value = UiState.Success
                    _userProfile.value = result
                } else {
                    uiState.value = UiState.Error(message = "Unknown Error")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                uiState.value = UiState.Error(message = e.message ?: "Unknown error")
            }
        }
    }
}