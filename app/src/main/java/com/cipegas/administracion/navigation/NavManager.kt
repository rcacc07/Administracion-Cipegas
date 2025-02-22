package com.cipegas.administracion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cipegas.administracion.presentation.CobranzaViewModel
import com.cipegas.administracion.presentation.CobranzasScreen
import com.cipegas.administracion.presentation.HomeScreen
import com.cipegas.administracion.presentation.HomeViewModel
import com.cipegas.administracion.presentation.LoanViewModel
import com.cipegas.administracion.presentation.OptionScreen
import com.cipegas.administracion.presentation.OptionViewModel
import com.cipegas.administracion.presentation.PrestamosScreen

@Composable
fun NavManager(navigationController : NavHostController){
    NavHost(
        navController = navigationController ,
        startDestination = "Home"
    ) {

        composable("Home"){
            OptionScreen(navigateTo  = {optionId ->
                navigationController.navigate(Routes.Option.createOption(optionId))
            } )
        }
        composable("Banks"){
            HomeScreen()
        }
        composable("Loans"){
            PrestamosScreen()
        }
        composable("Chargue"){
            CobranzasScreen()
        }
    }
}

sealed class Routes(val route : String){
    object Home : Routes("Banks")
    object Option : Routes("option/{optionId}"){

        fun createOption(optionId : String) : String{
            return optionId
        }

    }
}