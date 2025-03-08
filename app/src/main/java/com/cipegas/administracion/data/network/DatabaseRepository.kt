package com.cipegas.administracion.data.network

import android.util.Log
import com.cipegas.administracion.domain.model.BankItem
import com.cipegas.administracion.domain.model.BillItem
import com.cipegas.administracion.domain.model.ChargeItem
import com.cipegas.administracion.domain.model.ClientItem
import com.cipegas.administracion.domain.model.LoanItem
import com.cipegas.administracion.domain.model.OptionItem
import com.cipegas.administracion.domain.model.ProviderItem
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
        const val PROVIDER_COLLECTION = "provedores"
    }

    fun getCharge() : Flow<List<ChargeItem>>{
        return db.collection(CHARGE_COLLECTION)
            .snapshots()
            .map { qs -> qs.toObjects(ChargueResponse::class.java)
                .mapNotNull { cr -> chargeToDomain(cr) }}
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

    suspend fun getProviders() : List<ProviderItem> {

        val providersResponse : MutableList<ProviderResponse> = arrayListOf()

        db.collection(PROVIDER_COLLECTION)
            .get()
            .addOnSuccessListener { snapshot ->
                for (document in snapshot){
                    val providerResponse = document.toObject<ProviderResponse>()
                    providersResponse.add(providerResponse)
                }
            }
            .addOnFailureListener { exception ->
                Log.d("CIPEGAS CARO", "get failed with", exception)
            }
            .await()

        return providersToDomain(providersResponse)
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

            val li = LoanItem( quotas , l.title.toString(), l.fecDesem.toString(),l.amount)
            loansDomain.add(li)

        }

        return loansDomain
    }

    fun providersToDomain(providersResp: List<ProviderResponse>) : List<ProviderItem>{

        val providersDomain : MutableList<ProviderItem> = arrayListOf()

        providersResp.forEach { l ->

            val facts : MutableList<BillItem> = arrayListOf()

            l.facts?.forEach { q ->
                val qi = BillItem(q.amount,q.date,q.expirationDate,q.number,q.product,q.state,q.client)
                facts.add(qi)
            }

            val pi = ProviderItem(facts,l.name.toString(),0.0)
            providersDomain.add(pi)

        }

        return providersDomain
    }

    fun chargeToDomain(chargesResp : ChargueResponse) : ChargeItem {

        val clients : MutableList<ClientItem> = arrayListOf()
        val ci :ChargeItem?

        chargesResp.clients?.forEach { c ->
            val cl = ClientItem(c.name,c.amount)
            clients.add(cl)
        }

        ci = ChargeItem(clients,chargesResp.date.toString())
        return ci
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