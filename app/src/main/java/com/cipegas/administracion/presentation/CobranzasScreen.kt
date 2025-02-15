package com.cipegas.administracion.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        //uiState.charges?.let { it1 -> Cobranzas(it1.clients,it) }

        Cobranzas(uiState.charges!!.clients,it)
    }
}



@Composable
fun Cobranzas(client: List<ClientItem>, paddingValues: PaddingValues) {

    var suma = 0.0

    LazyColumn (
        modifier = Modifier
            .padding(paddingValues)
    ) {
        items(client){ client ->
            suma += client.amount
            CardCobranzasItem(client)
        }


        item {
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .wrapContentHeight(),
                horizontalArrangement = Arrangement.SpaceBetween,

            ){
                Text(
                    text = "Total",
                    modifier = Modifier.weight(1.5f),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)

                val amountTotal = String.format("%-,20.2FM", suma.toBigDecimal())
                Text(
                    text = "S/." + amountTotal,
                    modifier = Modifier.weight(1f),
                    color = Color.Black,
                    textAlign = TextAlign.Left,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp)


            }
        }
    }

}

@SuppressLint("DefaultLocale")
@Composable
fun CardCobranzasItem(client: ClientItem) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .wrapContentHeight(),
       horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = client.name.toString(),
            modifier = Modifier.weight(1.5f),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp)

        val amount = String.format("%-,20.2fL", client.amount)

        Text(
            text = "S/."+ amount,
            modifier = Modifier.weight(1f).
            background(Color.Blue)
                .fillMaxWidth(),
            color = Color.Red,
            style = TextStyle(textAlign = TextAlign.Left),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp)
    }
}
