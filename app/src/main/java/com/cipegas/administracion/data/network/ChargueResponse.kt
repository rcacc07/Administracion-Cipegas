package com.cipegas.administracion.data.network

data class ChargueResponse(

    val clients : List<ClientResponse> ?= null,
    val title : String ?= null,
    val fecDesem : String ?= null,
)
