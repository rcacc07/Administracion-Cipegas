package com.cipegas.administracion.presentation


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cipegas.administracion.components.MainTopBar
import com.cipegas.administracion.domain.model.LoanItem
import java.text.DecimalFormat

@Composable
fun PrestamosScreen(loanViewModel: LoanViewModel  = hiltViewModel()) {

    val uiState : LoanUiState by loanViewModel.uiState.collectAsState()

    Scaffold (
        topBar = {
            MainTopBar(title = "Prestamos" , onClickBackButton = {}) {
                //
            }
        }
    ){
        Loans(uiState.loans,it)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Loans(loans : List<LoanItem>, paddingValues: PaddingValues) {

    LazyColumn(
        modifier = Modifier.padding(paddingValues)
    ) {
        loans.forEachIndexed{ index,sectionedItem ->

            stickyHeader {
                Column (
                    modifier = Modifier
                        .height(60.dp)
                        .fillMaxWidth()
                        .background(Color.LightGray)
                ){
                    Box(modifier = Modifier.padding(4.dp))
                    {
                        Text(text = (index+1).toString() +" "+sectionedItem.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold)


                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = sectionedItem.fecDesem, fontSize = 16.sp)
                }
            }

            item {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TableCell(text = "Fecha Ven", weight = .2f, alignment = TextAlign.Center, title = true)
                    TableCell(text = "Monto", weight = .2f, alignment = TextAlign.Center, title = true)
                    TableCell(text = "Estado", weight = .2f, alignment = TextAlign.Center, title = true)
                }
            }
            items(sectionedItem.quotas){ it ->
                val amount = it.amount.toString().toDouble()
                val dec = DecimalFormat("#,###.00")


                Row {
                    TableCell(text = it.fecVen.toString(), weight = .2f,alignment = TextAlign.Center)
                    TableCell(text = "S/. " + dec.format(amount), weight = .2f,alignment = TextAlign.Center)
                    TableCellItem(text = it.estado.toString() , weight = .2f,alignment = TextAlign.Center , state = true)
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
fun RowScope.TableCell(
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
fun RowScope.TableCellItem(
    text:String,
    weight : Float,
    alignment: TextAlign = TextAlign.Center,
    state: Boolean = false){

    val color = if (text.toString().equals("Pagado")) Color.Black else Color.Red

    Text(
        text = text,
        Modifier
            .weight(weight)
            .padding(10.dp),
        color = color,
        fontSize = 12.sp,
        fontWeight = if (state) FontWeight.Bold else FontWeight.Normal,
        textAlign = alignment,
    )
}
