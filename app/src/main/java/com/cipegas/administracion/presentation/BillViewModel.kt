package com.cipegas.administracion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cipegas.administracion.data.network.DatabaseRepository
import com.cipegas.administracion.domain.model.FactsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BillViewModel @Inject constructor(val db : DatabaseRepository): ViewModel() {

    var _uiState : MutableStateFlow<BillState> = MutableStateFlow(BillState())
    val uiState : StateFlow<BillState> = _uiState

    init {
        viewModelScope.launch {

        }
    }

}

data class BillState(
    val isLoading : Boolean = false,
    val bills : List<FactsItem> = emptyList(),
    var idClient : String = ""
)