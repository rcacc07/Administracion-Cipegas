package com.cipegas.administracion.domain.repository

import com.cipegas.administracion.domain.model.ChargeItem
import com.cipegas.administracion.util.Response
import kotlinx.coroutines.flow.Flow

interface ChargueListScreenRepository {

     fun loadChargueList() : Flow<Response<ChargeItem>>
}