package com.cipegas.administracion.domain.model

data class BankItem(
    val id: String,
    val name: String,
    val amount : Double,
    val date : String
)

data class BankList(
    val id: Int,
    val name : String
)
