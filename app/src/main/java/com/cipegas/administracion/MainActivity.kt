package com.cipegas.administracion

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.cipegas.administracion.presentation.HomeScreen
import com.cipegas.administracion.presentation.HomeViewModel
import com.cipegas.administracion.ui.theme.AdministracionTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AdministracionTheme {
                Surface {
                    val homeViewModel : HomeViewModel by viewModels<HomeViewModel>()
                    HomeScreen(homeViewModel)
                }
            }
        }
    }
}
