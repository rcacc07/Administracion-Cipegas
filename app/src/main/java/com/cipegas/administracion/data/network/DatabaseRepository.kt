package com.cipegas.administracion.data.network

import com.cipegas.administracion.domain.model.BankItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseRepository @Inject constructor(val db : FirebaseFirestore) {

    companion object{
        const val USER_COLLECTION = "bancos"
        const val FIELD_DATE = "date"
    }


    fun getBanks() : Flow<List<BankItem>>{
        return db.collection(USER_COLLECTION)
            .snapshots()
            .map { qs -> qs.toObjects(BankResponse::class.java).mapNotNull { bankResponse ->
                bankToDomain(bankResponse) }
            }
    }

    fun bankToDomain(br: BankResponse) : BankItem?{
        if (br.amount == null)
            return null

        val bm = BankItem(id = br.id.toString(),
            name = br.name.toString(),
            amount = br.amount,
            date = "22")

        return bm
    }
}