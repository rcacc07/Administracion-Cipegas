package com.cipegas.administracion.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cipegas.administracion.data.network.DatabaseRepository
import com.cipegas.administracion.domain.model.OptionItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OptionViewModel @Inject constructor(val db : DatabaseRepository) : ViewModel(){

    var _uiState : MutableStateFlow<OptionUiState> = MutableStateFlow<OptionUiState>(OptionUiState())
    val uiState : StateFlow<OptionUiState> = _uiState

    init {
        viewModelScope.launch {
            db.optionCipegas().collect{ options ->
                _uiState.update {
                    it.copy(
                        options = options
                    )
                }

            }

        }
    }

}

data class OptionUiState(
    val options :List<OptionItem> = emptyList()

)