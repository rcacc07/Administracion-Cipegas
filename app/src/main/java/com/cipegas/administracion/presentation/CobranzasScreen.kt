package com.cipegas.administracion.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cipegas.administracion.components.MainTopBar
import com.cipegas.administracion.domain.model.ClientItem
import java.text.DecimalFormat


@Composable
fun CobranzasScreen(cobranzasVM: CobranzaViewModel = hiltViewModel()) {

    val uiState : CobranzasUiState by cobranzasVM.uiState.collectAsState()
    Scaffold (
        topBar = {
            MainTopBar(title = "COBRANZAS" , onClickBackButton = {}) {
                //
            }
        }
    ){
        if(uiState.charge.isNotEmpty()){
            Cobranzas(uiState.charge.get(0).clients,it)
        }
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
                    text = "TOTAL =",
                    modifier = Modifier.weight(2f),
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(textAlign = TextAlign.Center),
                    fontSize = 16.sp)

                val formatter = DecimalFormat("#,###,##0.00")
                val amountTotal = formatter.format(suma)
                Text(
                    text = "S/.".plus(amountTotal),
                    modifier = Modifier.weight(1f),
                    color = Color.Black,
                    style = TextStyle(textAlign = TextAlign.Left),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp)


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
            modifier = Modifier.weight(2f),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            style = TextStyle(textAlign = TextAlign.Left),
            fontSize = 12.sp)

        Text(
            text = "S/.".plus(String.format("%-,20.2f", client.amount)).trim(),
            modifier = Modifier.weight(1f)
                .fillMaxWidth(),
            color = Color.Red,
            style = TextStyle(textAlign = TextAlign.Left),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp)
    }
}
