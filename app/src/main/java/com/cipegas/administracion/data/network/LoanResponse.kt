package com.cipegas.administracion.data.network


data class LoanResponse(
    val quotas : List<CuotaResponse> ?= null,
    val title : String ?= null,
    val fecDesem : String ?= null,

)