package com.cipegas.administracion.data.network

import java.io.Serializable

data class BankResponse(
    val id: String? = null,
    val name: String? = null,
    val amount : Double? = null,
) : Serializable
