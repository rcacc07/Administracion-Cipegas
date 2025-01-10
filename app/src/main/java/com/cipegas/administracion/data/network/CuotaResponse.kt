package com.cipegas.administracion.data.network

data class CuotaResponse(
    val amount : String ?= null,
    val estado : String ?= null,
    val fecVen : String ?= null,
)
