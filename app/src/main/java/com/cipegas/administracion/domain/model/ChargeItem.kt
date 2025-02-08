package com.cipegas.administracion.domain.model

data class ChargeItem(
    val clients : List<ClientItem>,
    val title : String ? = null,
    val amount: String ? = null,
)
