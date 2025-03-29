package com.cipegas.administracion.domain.model

data class FactsItem (
    val fatcs : List<BillItem>,
    val id : String,
    var name : String,
    var amountTotal : Double
)