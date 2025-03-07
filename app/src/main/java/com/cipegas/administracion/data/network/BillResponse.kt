package com.cipegas.administracion.data.network

data class BillResponse(
    val amount : Double = 0.0,
    val date : String ?= null,
    val expirationDate : String ?= null,
    val number : String ?= null,
    val product : String ?= null,
    val state : String ?= null,
    val client : String ?=null,
)
