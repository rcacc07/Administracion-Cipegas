package com.cipegas.administracion.domain.model

import com.cipegas.administracion.data.network.BillResponse

data class OweItem(
    val facts : List<BillItem>,
    val id : String ,
    val name : String
)
