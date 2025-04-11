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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cipegas.administracion.components.MainTopBar
import com.cipegas.administracion.domain.model.BankItem


@Composable
fun BankScreen(homeViewModel: HomeViewModel = hiltViewModel(),
               navigateToBack : () -> Unit ) {

    val uiState : HomeUiState by homeViewModel.uiState.collectAsState()
    Scaffold (
        topBar = {
            MainTopBar(title = "BANCOS" ,
                showBackButton = true,
                onClickBackButton = {navigateToBack()}) {

            }
        }
    ){
        Banks(uiState.banks,it)
    }
}



@Composable
fun Banks(banks : List<BankItem> , paddingValues: PaddingValues) {

    LazyColumn (
        modifier = Modifier.padding(paddingValues)
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
            .padding(12.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = bank.name.trim(),
            modifier = Modifier.weight(2f),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            style = TextStyle(textAlign = TextAlign.Left),
            fontSize = 12.sp)

        Text(
            text = "S/.".plus(String.format("%-,20.2f", bank.amount)).trim(),
            modifier = Modifier.weight(1f)
                .fillMaxWidth(),
            color = Color.Red,
            style = TextStyle(textAlign = TextAlign.Left),
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp)

    }
}
