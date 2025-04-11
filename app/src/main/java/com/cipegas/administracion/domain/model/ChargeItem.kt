package com.cipegas.administracion.domain.model

data class ChargeItem(
    val facts : List<BillItem> = emptyList(),
    val date : String ?= null,
    val name : String ?=null,
    val amountTot : Double = 0.0,
    val id : Int =  0

)
