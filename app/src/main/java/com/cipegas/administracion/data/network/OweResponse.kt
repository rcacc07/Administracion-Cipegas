package com.cipegas.administracion.data.network

import com.cipegas.administracion.domain.model.BillItem

data class OweResponse(
    val facts : List<BillItem> ?= null,
    val id : String ?= null,
    val name : String ?= null
)
