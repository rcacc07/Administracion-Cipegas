package com.cipegas.administracion.domain.model

data class FactsItem (
    val fatcs : List<BillItem>,
    val name : String,
    var amountTotal : Double
)