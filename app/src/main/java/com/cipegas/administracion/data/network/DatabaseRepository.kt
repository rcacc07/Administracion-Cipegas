package com.cipegas.administracion.data.network

import android.util.Log
import com.cipegas.administracion.domain.model.BankItem
import com.cipegas.administracion.domain.model.ChargeItem
import com.cipegas.administracion.domain.model.ClientItem
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
        const val OPTION_COLLECTION = "opciones"
        const val CHARGE_COLLECTION = "cobranzas"

    }

    suspend fun getCharge() : List<ChargeItem> {

        val chargesResponse : MutableList<ChargueResponse> = arrayListOf()
        db.collection(CHARGE_COLLECTION)
            .get()
            .addOnSuccessListener { snapshot ->

                Log.d("CIPEGAS CARO CHARGUE", snapshot.toString())
                for (doc in snapshot){
                    val chargeResponse = doc.toObject<ChargueResponse>()
                    chargesResponse.add(chargeResponse)
                }
            }
            .addOnFailureListener { exception ->
                Log.d("CIPEGAS CARO CHARGUE", "get failed with", exception)
            }
            .await()

        return chargeToDomain(chargesResponse)
    }

    fun optionCipegas() : Flow<List<OptionItem>>{
        return db.collection(OPTION_COLLECTION)
            .snapshots()
            .map { qs -> qs.toObjects(OptionResponse::class.java).mapNotNull {
                    optionResponse -> optionDomain(optionResponse)
            } }
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
            }
            .addOnFailureListener { exception ->
                Log.d("CIPEGAS CARO", "get failed with", exception)
            }
            .await()

        return loansToDomain(loansResponse)
    }

    fun getBanks() : Flow<List<BankItem>>{
        return db.collection(USER_COLLECTION)
            .snapshots()
            .map { qs -> qs.toObjects(BankResponse::class.java).mapNotNull { bankResponse ->
                bankToDomain(bankResponse) }
            }
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

    fun chargeToDomain(chargesResp : List<ChargueResponse>) : List<ChargeItem> {

        val clients : MutableList<ClientItem> = arrayListOf()
        val chargues : MutableList<ChargeItem> = arrayListOf()
        var ci :ChargeItem?
        chargesResp.forEach { cl ->

            cl.clients?.forEach{ c ->
                val cl = ClientItem(c.name,c.amount)
                clients.add(cl)
            }

            ci = ChargeItem(clients,cl.date.toString())
            chargues.add(ci!!)

        }

        return chargues
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