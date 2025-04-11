package com.cipegas.administracion.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.cipegas.administracion.components.MainTopBar
import com.cipegas.administracion.domain.model.ProviderItem
import java.text.DecimalFormat


@Composable
fun ProviderScreen(providerViewModel: ProviderViewModel = hiltViewModel()) {

    val uiState : ProviderState by providerViewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            MainTopBar(title = "Provedores" , onClickBackButton = {}) { }
        }
    ) {
        Providers(uiState.providers,it)

    }
}

@SuppressLint("DefaultLocale")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Providers(providers : List<ProviderItem>, paddingValues: PaddingValues) {

    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        providers.forEach { p->
            var sumTotal = 0.0
            p.fatcs.forEach {
                if (it.state.equals("pendiente",true)) {
                    sumTotal+=it.amount
                }

            }
            p.amountTotal = sumTotal
        }

        providers.forEachIndexed{ index,sectionedItem ->

            stickyHeader {
                Row (
                    modifier = Modifier
                        .background(Color.LightGray)
                        .padding(4.dp),

                    verticalAlignment = Alignment.CenterVertically
                    )
                {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = sectionedItem.name,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                    )
                    Column (
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                        )
                    {

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Deuda Total",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        val formatter = DecimalFormat("#,###,##0.00")
                        val amountTotal = formatter.format(sectionedItem.amountTotal)

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "S./".plus(amountTotal),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                    }
                }
            }

            item {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TableCellProvider(text = "NUM FACT", weight = .2f, alignment = TextAlign.Center)
                    TableCellProvider(text = "DESCRIPCION", weight = .2f, alignment = TextAlign.Center)
                    TableCellProvider(text = "MONTO", weight = .2f, alignment = TextAlign.Center)
                }
            }

            items(sectionedItem.fatcs){ it ->

                Row (modifier = Modifier.padding(bottom = 2.dp , top = 2.dp)) {
                    Column (
                        modifier = Modifier
                            .weight(.2f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it.number.toString().trim(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it.date.toString().trim(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column (
                        modifier = Modifier
                            .weight(.2f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it.client.toString().trim(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it.expirationDate.toString().trim(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )
                    }
                    Column (
                        modifier = Modifier
                            .weight(.2f)
                            .fillMaxWidth()
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "S/.".plus(String.format("%-,20.2f", it.amount)).trim(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center
                        )

                        val textColor = if (it.state.toString().trim().equals("Pagado",true)) Color.Black else Color.Red

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it.state.toString().trim(),
                            color = textColor,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                    }

                }

                HorizontalDivider(
                    color = Color.LightGray,
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                )
            }
        }

    }

}

@Composable
private fun CustomHeader(){

}

@Composable
fun RowScope.TableCellProvider(
    text:String,
    weight : Float,
    alignment: TextAlign = TextAlign.Center){

    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(10.dp),
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        textAlign = alignment,
    )
}
