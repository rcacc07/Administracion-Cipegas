package com.cipegas.administracion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cipegas.administracion.data.network.DatabaseRepository
import com.cipegas.administracion.domain.model.BankItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val db : DatabaseRepository) : ViewModel() {

    var _uiState : MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState())
    val uiState : StateFlow<HomeUiState> = _uiState

    init {
        viewModelScope.launch {
            db.getBanks().collect{ banks ->
                _uiState.update { state ->
                    state.copy(
                        banks = banks,
                        totalAmount =   banks.sumOf { it.amount }.toString().format("%.2f$")
                    )
                }
            }
        }
    }
}

data class HomeUiState(
    val isLoading : Boolean = false,
    val banks : List<BankItem> = emptyList(),
    val totalAmount : String? = null
)