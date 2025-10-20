package com.pkmn.app.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pkmn.app.domain.model.Pokemon
import com.pkmn.app.domain.repository.PokemonRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: PokemonRepository
): ViewModel() {
    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    var uiState = MutableStateFlow<SearchUiState>(SearchUiState.Idle)
        private set

    init {
        uiState.value = SearchUiState.Idle
    }

    fun onQueryChange(newQuery: String) {
        /*if (newQuery.length < 3) {
            uiState.value = SearchUiState.Idle
            return
        }*/

        _query.value = newQuery

        viewModelScope.launch {
            uiState.value = SearchUiState.Loading
            try {
                _query.debounce(500)
                    .filter { it.length >= 3 }
                    .distinctUntilChanged()
                    .collectLatest { keyword ->
                        val result = repository.getPokemonDetail(keyword)
                        uiState.value = SearchUiState.Success(result)
                    }
            } catch (e : Exception) {
                uiState.value = SearchUiState.Error(e.message.toString())
            }
        }
    }

    fun clearAll() {
        _query.value = ""
        uiState.value = SearchUiState.Idle
    }
}

