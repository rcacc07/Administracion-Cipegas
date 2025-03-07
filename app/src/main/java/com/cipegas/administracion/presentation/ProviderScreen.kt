package com.cipegas.administracion.presentation

import android.annotation.SuppressLint
import android.inputmethodservice.Keyboard.Row
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
        //val amount = it.amount.toString().toDouble()
        //val dec = DecimalFormat("#,###.00")

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
                Column (
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray)

                ){
                    Row(
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically
                    )
                    {
                        Text(
                            text = (index+1).toString() +". "+sectionedItem.name,
                            fontSize = 16.sp,
                            modifier = Modifier.weight(4f),
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Left
                        )


                        val formatter = DecimalFormat("#,###,##0.00")
                        val amountTotal = formatter.format(sectionedItem.amountTotal)

                        Text(
                            text = "S./".plus(amountTotal),
                            fontSize = 16.sp,
                            modifier = Modifier.weight(2f),
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
                    TableCellProvider(text = "Num Fact", weight = .1f, alignment = TextAlign.Center, title = true)
                    TableCellProvider(text = "Fecha Emision", weight = .1f, alignment = TextAlign.Center, title = true)
                    TableCellProvider(text = "Monto", weight = .2f, alignment = TextAlign.Center, title = true)
                }
            }

            items(sectionedItem.fatcs){ it ->

                Row {
                    TableCellProvider(text = it.number.toString(), weight = .1f,alignment = TextAlign.Center)

                    TableCellProvider(text = it.date.toString(), weight = .1f,alignment = TextAlign.Center)

                    Column (
                        modifier = Modifier
                            .weight(.2f)
                            .fillMaxWidth()

                    ) {

                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "S/.".plus(String.format("%-,20.2f", it.amount)).trim(),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Normal,
                            textAlign = TextAlign.Center

                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it.state.toString().trim(),
                            fontSize = 8.sp,
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
fun RowScope.TableCellProvider(
    text:String,
    weight : Float,
    alignment: TextAlign = TextAlign.Center,
    title: Boolean = false){

    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(10.dp),
        fontSize = 12.sp,
        fontWeight = if (title) FontWeight.Bold else FontWeight.Normal,
        textAlign = alignment,
    )
}

@Composable
fun RowScope.TableCellProviderItem(
    textAmount:String,
    textState:String,
    weight : Float,
    alignment: TextAlign = TextAlign.Center,
    ){

    val color = if (textState.contentEquals("Pagado")) Color.Black else Color.Red

        Text(
            text = textAmount ,
            Modifier
                .weight(weight)
                .padding(10.dp),
            color = color,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            textAlign = alignment,
        )
        Text(
            text = textState,
            Modifier
                .weight(weight)
                .padding(10.dp),
            color = color,
            fontSize = 8.sp,
            fontWeight = FontWeight.Bold,
            textAlign = alignment,
        )




}