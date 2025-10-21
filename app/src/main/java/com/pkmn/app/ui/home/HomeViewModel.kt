package com.pkmn.app.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pkmn.app.domain.model.Pokemon
import com.pkmn.app.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList = _pokemonList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    init {
        loadPokemons()
    }

    fun loadPokemons(limit: Int = 20, offset: Int = 0) {
        if (isLoading.value) {
            return
        }
        viewModelScope.launch {
            repository.getPokemonList(limit, offset)
                .onStart { _isLoading.value = true }
                .onCompletion { _isLoading.value = false }
                .catch { e -> e.printStackTrace() }
                .collect { it -> _pokemonList.value += it }
        }
    }
}