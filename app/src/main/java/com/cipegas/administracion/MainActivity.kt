package com.cipegas.administracion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.cipegas.administracion.navigation.NavManager
import com.cipegas.administracion.presentation.HomeScreen
import com.cipegas.administracion.presentation.HomeViewModel
import com.cipegas.administracion.presentation.LoanViewModel
import com.cipegas.administracion.presentation.OptionScreen
import com.cipegas.administracion.presentation.OptionViewModel
import com.cipegas.administracion.ui.theme.AdministracionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val optionVM : OptionViewModel by viewModels()
        val banksVM : HomeViewModel by viewModels()
        val loansVM : LoanViewModel by viewModels()

        setContent {
            AdministracionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val homeViewModel : HomeViewModel by viewModels<HomeViewModel>()
//                    HomeScreen(homeViewModel)
                    //val optionsViewModel : OptionViewModel by viewModels<OptionViewModel>()
                    //OptionScreen(optionsViewModel)
                    NavManager(optionVM, banksVM , loansVM)

                }
            }
        }
    }
}
