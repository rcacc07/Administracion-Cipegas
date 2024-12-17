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
import com.cipegas.administracion.components.MainTopBar
import com.cipegas.administracion.domain.model.BankItem


@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val uiState : HomeUiState by homeViewModel.uiState.collectAsState()
    Scaffold (
        topBar = {
            MainTopBar(title = "BANCOS" , onClickBackButton = {}) {
                //
            }
        }
    ){
        Banks(uiState.banks,it)
    }
}



@Composable
fun Banks(banks : List<BankItem> , paddingValues: PaddingValues) {

    LazyColumn (
        modifier = Modifier
            .padding(paddingValues)
    ) {
        items(banks){ bank ->
            CardBankItem(bank)
        }
    }

}

@SuppressLint("DefaultLocale")
@Composable
fun CardBankItem(bank: BankItem) {

    Row(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = bank.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            ///Text(text = bank.date, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        val amount = String.format("%-,10.2f", bank.amount)

        Text(
            text = "S/. $amount",
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp)
    }
}
