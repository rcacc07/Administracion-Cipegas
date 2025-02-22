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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.cipegas.administracion.navigation.NavManager
import com.cipegas.administracion.presentation.CobranzaViewModel
import com.cipegas.administracion.presentation.HomeScreen
import com.cipegas.administracion.presentation.HomeViewModel
import com.cipegas.administracion.presentation.LoanViewModel
import com.cipegas.administracion.presentation.OptionScreen
import com.cipegas.administracion.presentation.OptionViewModel
import com.cipegas.administracion.ui.theme.AdministracionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navigationController : NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AdministracionTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    navigationController = rememberNavController()
                    NavManager(navigationController)

                }
            }
        }
    }
}
