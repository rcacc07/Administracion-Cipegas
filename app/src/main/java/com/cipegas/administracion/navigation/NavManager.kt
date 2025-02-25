package com.cipegas.administracion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cipegas.administracion.presentation.BankScreen
import com.cipegas.administracion.presentation.CobranzasScreen
import com.cipegas.administracion.presentation.OptionScreen
import com.cipegas.administracion.presentation.PrestamosScreen

@Composable
fun NavManager(navigationController : NavHostController){
    NavHost(
        navController = navigationController ,
        startDestination = Routes.Option.route
    ) {
        composable(Routes.Option.route){
            OptionScreen(navigateTo  = {optionId ->
                navigationController.navigate(Routes.Option.createOption(optionId))
            } )
        }
        composable(Routes.Bank.route){
            BankScreen()
        }
        composable(Routes.Loans.route){
            PrestamosScreen()
        }
        composable(Routes.Chargue.route){
            CobranzasScreen()
        }
    }
}

sealed class Routes(val route : String){

    object Option : Routes("Option"){

        fun createOption(optionId : String) : String{
            return optionId
        }

    }
    object Bank : Routes("Banks")
    object Loans : Routes("Loans")
    object Chargue : Routes("Chargue")
}