package com.cipegas.administracion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cipegas.administracion.data.network.DatabaseRepository
import com.cipegas.administracion.domain.model.ProviderItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProviderViewModel @Inject constructor(val db : DatabaseRepository) : ViewModel() {

    var _uiState : MutableStateFlow<ProviderState> = MutableStateFlow(ProviderState())
    val uiState : StateFlow<ProviderState> = _uiState

    init {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    providers = db.getProviders()
                )
            }
        }
    }

}

data class ProviderState(
    val isLoading : Boolean = false,
    val providers : List<ProviderItem> = emptyList(),
)