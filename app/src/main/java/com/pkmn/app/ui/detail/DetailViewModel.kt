package com.pkmn.app.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pkmn.app.domain.model.Pokemon
import com.pkmn.app.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: PokemonRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val pokemonName: String = savedStateHandle["pokemonName"] ?: ""

    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon = _pokemon.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()

    init {
        loadPokemonDetail()
    }

    fun loadPokemonDetail() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val result = repository.getPokemonDetail(pokemonName)
                _pokemon.value = result
            } catch (e: Exception) {
                e.printStackTrace()
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }
}
