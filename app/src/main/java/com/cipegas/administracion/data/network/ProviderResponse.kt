package com.cipegas.administracion.data.network

data class ProviderResponse(
    val facts : List<BillResponse> ?= null,
    val name : String ?= null,

)
