package com.cipegas.administracion.data.network

import com.cipegas.administracion.domain.model.BillItem

data class ChargueResponse(

    //val clients : List<OweResponse> ?= null,
    val facts : List<BillItem> ?= null,
    val date : String ?= null,
    val name : String ?=null,
    val id : String?= null
)
