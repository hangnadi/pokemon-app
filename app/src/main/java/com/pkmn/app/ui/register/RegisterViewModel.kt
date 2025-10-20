package com.pkmn.app.ui.register

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pkmn.app.domain.repository.UserRepository
import com.pkmn.app.ui.search.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RegisterUiData(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorMessage: String = "",
)

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    var uiState = MutableStateFlow<RegisterUiState>(RegisterUiState.Idle)
        private set
    private val _uiData = MutableStateFlow(RegisterUiData())
    val uiData = _uiData.asStateFlow()

    init {
        uiState.value = RegisterUiState.Idle
    }

    fun onNameChange(value: String) {
        _uiData.update { it.copy(name = value) }
    }

    fun onEmailChange(value: String) {
        _uiData.update { it.copy(email = value) }
    }

    fun onPasswordChange(value: String) {
        _uiData.update { it.copy(password = value) }
    }

    fun onConfirmPasswordChange(value: String) {
        _uiData.update { it.copy(confirmPassword = value) }
    }

    fun registerUser() {
        val temp = _uiData.value

        if (temp.name.isBlank() || temp.email.isBlank() || temp.password.isBlank()) {
            _uiData.update { it.copy(errorMessage = "All fields are required") }
            return
        }

        if (Patterns.EMAIL_ADDRESS.matcher(temp.email).matches()) {
            _uiData.update { it.copy(errorMessage = "Invalid Email") }
            return
        }

        if (temp.password.length < 8 || temp.password.length > 32
            || (temp.password.any { it.isDigit() } && temp.password.any { it.isUpperCase() })
            ) {
            _uiData.update { it.copy(errorMessage = "Invalid password") }
            return
        }

        if (temp.confirmPassword != temp.password) {
            _uiData.update { it.copy(errorMessage = "Password not match") }
            return
        }

        viewModelScope.launch {
            uiState.value = RegisterUiState.Loading

            try {
                val result = repository.userRegister(
                    name = temp.name,
                    email = temp.email,
                    password = temp.password
                )

                if (result) {
                    uiState.value = RegisterUiState.Success
                } else {
                    uiState.value = RegisterUiState.Idle
                    _uiData.update { it.copy(errorMessage = "Unknown Error") }
                }

            } catch (e: Exception) {
                e.printStackTrace()
                uiState.value = RegisterUiState.Idle
                _uiData.update { it.copy(errorMessage = e.message ?: "Unknown error") }
            }
        }
    }
}