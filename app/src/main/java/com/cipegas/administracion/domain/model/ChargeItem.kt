package com.cipegas.administracion.domain.model

data class ChargeItem(
    var clients : List<ClientItem> = emptyList() ,
    var date : String  = "",

)
