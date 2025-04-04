package com.cipegas.administracion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cipegas.administracion.data.network.DatabaseRepository
import com.cipegas.administracion.domain.model.ChargeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CobranzaViewModel @Inject constructor(val db : DatabaseRepository) : ViewModel() {

    var _uiState : MutableStateFlow<CobranzasUiState> = MutableStateFlow(CobranzasUiState())
    val uiState : StateFlow<CobranzasUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    charges = db.getCharge(),
                )
            }
        }
    }

}

data class CobranzasUiState(
    val isLoading: Boolean = false,
    val charges: List<ChargeItem> = emptyList(),
)