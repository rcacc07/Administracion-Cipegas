package com.cipegas.administracion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cipegas.administracion.data.network.DatabaseRepository
import com.cipegas.administracion.domain.model.LoanItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoanViewModel @Inject constructor(val db : DatabaseRepository) : ViewModel() {

    var _uiState : MutableStateFlow<LoanUiState> = MutableStateFlow<LoanUiState>(LoanUiState())
    val uiState : StateFlow<LoanUiState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    loans = db.getLoans()
                )
            }
        }
    }
}

data class LoanUiState(
    val isLoading : Boolean = false,
    val loans : List<LoanItem> = emptyList(),

)