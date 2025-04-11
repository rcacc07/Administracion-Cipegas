package com.cipegas.administracion.domain.model

data class FactsItem (
    val fatcs : List<BillItem>,
    val id : Int = 0,
    var name : String,
    var amountTotal : Double
)