package com.pkmn.app.ui.register

import com.pkmn.app.domain.model.Pokemon

sealed class RegisterUiState {
    object Idle : RegisterUiState()
    object Loading : RegisterUiState()
    object Success : RegisterUiState()

}