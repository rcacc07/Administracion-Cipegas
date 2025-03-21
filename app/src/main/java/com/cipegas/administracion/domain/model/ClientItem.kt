package com.cipegas.administracion.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ClientItem(
    val name: String ? = null,
    val amount : Double = 0.0,
    val id : String ?= null,
)
