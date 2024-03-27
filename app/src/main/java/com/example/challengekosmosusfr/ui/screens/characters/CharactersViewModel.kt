package com.example.challengekosmosusfr.ui.screens.characters

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challengekosmosusfr.data.RickAndMortyRepository
import com.example.challengekosmosusfr.domain.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) : ViewModel() {

    private val coroutineExceptionHandlerCharacters = CoroutineExceptionHandler { _, exception ->
        Log.e("error", "coroutineExceptionHandler ${exception.localizedMessage}")
        _state.value = _state.value.copy(currentError = exception.message ?: "")
        stopByError()
    }

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state

    private val scope = CoroutineScope(Job())
    init {
        viewModelScope.launch {
            _state.value = UiState(isShowLoading = true)
            scope.launch(Dispatchers  .IO + coroutineExceptionHandlerCharacters) {
                _state.value = _state.value.copy(
                    characters = rickAndMortyRepository.getCharacters(
                        pageNumber = _state.value.currentPageNumber
                    )
                )
                hideLoading()
            }
        }
    }

    private fun stopByError() {
        scope.cancel()
        hideLoading()
    }

    private fun hideLoading() {
        viewModelScope.launch {
            delay(2000)
            _state.value = _state.value.copy(isShowLoading = false)
        }
    }

    data class UiState(
        val isShowLoading: Boolean = true,
        val characters: List<Character> = emptyList(),
        val currentError: String = "",
        val currentPageNumber: Int = 1
    )
}