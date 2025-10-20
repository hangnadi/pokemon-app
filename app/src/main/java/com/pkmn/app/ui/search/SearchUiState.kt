package com.pkmn.app.ui.search

import com.pkmn.app.domain.model.Pokemon

sealed class SearchUiState {
    object Idle : SearchUiState()
    object Loading : SearchUiState()
    data class Success(val pokemon: Pokemon) : SearchUiState()
    data class Error(val message: String) : SearchUiState()
}