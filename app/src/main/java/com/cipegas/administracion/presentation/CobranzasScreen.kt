package com.cipegas.administracion.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.cipegas.administracion.components.MainTopBar
import com.cipegas.administracion.domain.model.ClientItem


@Composable
fun CobranzasScreen(cobranzasVM: CobranzaViewModel , navController: NavController) {

    val uiState : CobranzasUiState by cobranzasVM.uiState.collectAsState()
    Scaffold (
        topBar = {
            MainTopBar(title = "COBRANZAS" , onClickBackButton = {}) {
                //
            }
        }
    ){
        uiState.charges?.let { it1 -> Cobranzas(it1.clients,it) }
    }
}



@Composable
fun Cobranzas(charges: List<ClientItem>, paddingValues: PaddingValues) {

    LazyColumn (
        modifier = Modifier
            .padding(paddingValues)
    ) {
        items(charges){ charge ->
            CardCobranzasItem(charge)
        }
    }

}

@SuppressLint("DefaultLocale")
@Composable
fun CardCobranzasItem(client: ClientItem) {

    Row(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            client.name?.let { Text(text = it, fontSize = 18.sp, fontWeight = FontWeight.Bold) }
            Spacer(modifier = Modifier.height(6.dp))
            ///Text(text = bank.date, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        val amount = String.format("%-,10.2f", client.amount)

        Text(
            text = "S/. $amount",
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)
    }
}
