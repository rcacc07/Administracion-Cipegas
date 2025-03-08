package com.cipegas.administracion.domain.model

data class LoanItem(
    val quotas : List<QuotaItem>,
    val title : String,
    val fecDesem : String,
    val amount : Double = 0.0
)