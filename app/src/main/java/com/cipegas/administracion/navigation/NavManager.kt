package com.cipegas.administracion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cipegas.administracion.presentation.HomeScreen
import com.cipegas.administracion.presentation.HomeViewModel
import com.cipegas.administracion.presentation.OptionScreen
import com.cipegas.administracion.presentation.OptionViewModel

@Composable
fun NavManager(optionVM : OptionViewModel, banksVM: HomeViewModel ){

    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = "Options") {
        composable("Options"){
            OptionScreen(optionVM,navController)
        }
        composable("Banks"){
            HomeScreen(banksVM,navController)
        }
    }
}