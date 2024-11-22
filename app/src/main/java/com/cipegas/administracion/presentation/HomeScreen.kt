package com.cipegas.administracion.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cipegas.administracion.R
import com.cipegas.administracion.domain.model.BankItem


@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {

    val uiState : HomeUiState by homeViewModel.uiState.collectAsState()
    Column {
        Text(
            text = "Cipegas",
            fontSize = 24.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 24.dp)
        )
        Banks(uiState.banks)
    }
}

@Composable
fun Banks(banks : List<BankItem>) {

    LazyColumn {
        items(banks){ bank ->
            CardBankItem(bank)
        }
    }

}

@Composable
fun CardBankItem(bank: BankItem) {
    Row(
        modifier = Modifier
            .padding(24.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "",
            modifier = Modifier.size(48.dp)
        )
        Spacer(modifier = Modifier.width(24.dp))
        Column {
            Text(text = bank.name, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(6.dp))
            Text(text = bank.date, fontSize = 14.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
        Text(text = String.format("%.2f$", bank.amount), color = Color.Red)
    }
}
