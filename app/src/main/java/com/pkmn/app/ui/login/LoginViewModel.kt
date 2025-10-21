package com.pkmn.app.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pkmn.app.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiData(
    val email: String = "",
    val password: String = "",
    val errorMessage: String = "",
)

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    var uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
        private set
    private val _uiData = MutableStateFlow(LoginUiData())
    val uiData = _uiData.asStateFlow()

    init {
        uiState.value = LoginUiState.Idle
    }

    fun onEmailChange(value: String) {
        _uiData.update {
            it.copy(email = value)
        }
    }

    fun onPasswordChange(value: String) {
        _uiData.update {
            it.copy(password = value)
        }
    }

    fun login() {
        val temp = _uiData.value

        if (temp.email.isBlank() || temp.password.isBlank()) {
            _uiData.update { it.copy(errorMessage = "All fields are required") }
            return
        }

        viewModelScope.launch {
            uiState.value = LoginUiState.Loading
            try {
                val result = repository.loginUser(temp.email, temp.password)
                if (result) {
                    uiState.value = LoginUiState.Success
                } else {
                    uiState.value = LoginUiState.Idle
                    _uiData.update {
                        it.copy(errorMessage = "Unknown Error")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                uiState.value = LoginUiState.Idle
                _uiData.update {
                    it.copy(errorMessage = e.message ?: "Unknown Error")
                }
            }
        }
    }

}