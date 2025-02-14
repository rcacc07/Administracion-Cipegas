package com.cipegas.administracion.data.network

import com.cipegas.administracion.domain.model.ChargeItem
import com.cipegas.administracion.domain.repository.ChargueListScreenRepository
import com.cipegas.administracion.util.Response
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ChargueListScreenRepositoryImpl @Inject constructor(
    private val db : FirebaseFirestore
) : ChargueListScreenRepository {
    override suspend fun loadChargueList(): Flow<Response<ChargeItem>> = callbackFlow {

        val chargesResponse : MutableList<ChargueResponse> = arrayListOf()

        db.collection("cobranzas")
            .get()
            .addOnSuccessListener { snapshot ->
                for (doc in snapshot){
                    val chargeResponse = doc.toObject<ChargueResponse>()
                    chargesResponse.add(chargeResponse)
                }

                Response.Succes(chargesResponse)

            }
            .addOnFailureListener {

            }.await()
    }
}