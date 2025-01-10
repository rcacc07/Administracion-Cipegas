package com.cipegas.administracion.data.network

import android.util.Log
import com.cipegas.administracion.domain.model.BankItem
import com.cipegas.administracion.domain.model.LoanItem
import com.cipegas.administracion.domain.model.OptionItem
import com.cipegas.administracion.domain.model.QuotaItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DatabaseRepository @Inject constructor(val db : FirebaseFirestore) {

    companion object{
        const val USER_COLLECTION = "bancos"
        const val LOANS_COLLECTION = "prestamos"
        const val FIELD_DATE = "date"

        const val OPTION_COLLECTION = "opciones"

    }

    suspend fun getLoans() : List<LoanItem> {

        val loansResponse : MutableList<LoanResponse> = arrayListOf()

        db.collection(LOANS_COLLECTION)
            .get()
            .addOnSuccessListener { snapshot ->
                for (document in snapshot){
                    val loanResponse = document.toObject<LoanResponse>()
                    loansResponse.add(loanResponse)

                }

                Log.d("CIPEGAS",  " total de prestamos " + loansResponse.count())
            }
            .addOnFailureListener { exception ->
                Log.d("CIPEGAS CARO", "get failed with", exception)
            }
            .await()

        return loansToDomain(loansResponse)

//            db.collection(LOANS_COLLECTION).document(loan.idFirebase.toString()).collection("cuotas").get()
//                .addOnSuccessListener { result ->
//                    for (doc in result){
//                        Log.d("CIPEGAS", doc.id + " = " + doc.data)
//                    }
//                }

        /* return db.collection(LOANS_COLLECTION)
            .snapshots()
            .map { qs -> qs.toObjects(LoanResponse::class.java).mapNotNull { LoanResponse ->
                loanToDomain(LoanResponse) }
            }  */
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

    fun loansToDomain(loansResp: List<LoanResponse>) : List<LoanItem>{

        val loansDomain : MutableList<LoanItem> = arrayListOf()

        loansResp.forEach { l ->

            val quotas : MutableList<QuotaItem> = arrayListOf()

            l.quotas?.forEach { q ->
                val qi = QuotaItem(q.amount,q.fecVen,q.estado)
                quotas.add(qi)
            }

            val li = LoanItem( quotas , l.title.toString(), l.fecDesem.toString())
            loansDomain.add(li)

        }

        return loansDomain
    }

    fun bankToDomain(br: BankResponse) : BankItem?{
        val bm = BankItem(id = br.id.toString(),
            name = br.name.toString(),
            amount = br.amount ?: 0.0,
            date = "22")

        return bm
    }

    fun optionDomain(op : OptionResponse) : OptionItem {
        val op = OptionItem(id = op.id ?: 0,
            name = op.opcion.toString())
        return op
    }
}