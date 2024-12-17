package com.cipegas.administracion.data.network

import android.util.Log
import com.cipegas.administracion.domain.model.BankItem
import com.cipegas.administracion.domain.model.OptionItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DatabaseRepository @Inject constructor(val db : FirebaseFirestore) {

    companion object{
        const val USER_COLLECTION = "bancos"
        const val FIELD_DATE = "date"

        const val OPTION_COLLECTION = "opciones"

    }

    fun getBanks() : Flow<List<BankItem>>{
        return db.collection(USER_COLLECTION)
            .snapshots()
            .map { qs -> qs.toObjects(BankResponse::class.java).mapNotNull { bankResponse ->
                bankToDomain(bankResponse) }
            }
    }

    fun optionCipegas() : Flow<List<OptionItem>>{
        return db.collection(OPTION_COLLECTION)
            .snapshots()
            .map { qs -> qs.toObjects(OptionResponse::class.java).mapNotNull {
                optionResponse -> optionDomain(optionResponse)
            } }
    }

    fun getBanksDemo(){
        db.collection("bancos").get().addOnSuccessListener {
            snapshop ->
            Log.i("Aristidev lectura","succes")
            snapshop.forEach { document ->
                Log.i("lecutra",document.data.toString())
            }
        }.addOnFailureListener {

        }
    }

    fun bankToDomain(br: BankResponse) : BankItem?{
        val bm = BankItem(id = br.id.toString(),
            name = br.name.toString(),
            amount = br.amount ?: 0.0,
            date = "22")

        return bm
    }

    fun optionDomain(op : OptionResponse) : OptionItem?{
        val op = OptionItem(id = op.id.toString(),
            name = op.opcion.toString())
        return op
    }
}