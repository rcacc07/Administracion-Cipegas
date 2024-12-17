package com.cipegas.administracion.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.cipegas.administracion.components.MainTopBar
import com.cipegas.administracion.domain.model.BankItem
import com.cipegas.administracion.domain.model.OptionItem

@Composable
fun OptionScreen(optionViewModel: OptionViewModel){

    val uiState : OptionUiState by optionViewModel.uiState.collectAsState()

    Scaffold (
        topBar = {
            MainTopBar(title = "CIPEGAS" , onClickBackButton = {}) {
                //
            }
        }
    ){
        OptionsList(uiState.options,it)
    }

}

@Composable
fun OptionsGridItem(title : String , onClick: () -> Unit){

    Row (
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ){

        Card(
            modifier = Modifier
                .width(150.dp)
                .height(100.dp)
                .padding(10.dp),

            shape = RoundedCornerShape(5.dp),
        ) {

            Text(
                text = title,
                textAlign = TextAlign.Center,
                color = Color.Red
            )

        }


    }


}


@Composable
fun OptionsList(optiones : List<OptionItem>, paddingValues: PaddingValues){

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(4.dp),
        modifier = Modifier.padding(paddingValues)
    )
    {
        items(optiones){
            item -> OptionsGridItem(item.name){

        }
        }
    }

}