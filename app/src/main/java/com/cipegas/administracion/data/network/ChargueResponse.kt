package com.cipegas.administracion.data.network

import com.cipegas.administracion.domain.model.BillItem

data class ChargueResponse(
    val facts : List<BillItem> ?= null,
    val date : String ?= null,
    val name : String ?=null,
    val id : Int = 0
)
