package com.cipegas.administracion.data.network

data class ChargueResponse(

    val clients : List<ClientResponse> ?= null,
    val date : String ?= null,
)
