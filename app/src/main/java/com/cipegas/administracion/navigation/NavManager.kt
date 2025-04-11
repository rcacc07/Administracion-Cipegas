package com.cipegas.administracion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cipegas.administracion.presentation.BankScreen
import com.cipegas.administracion.presentation.BillScreen
import com.cipegas.administracion.presentation.CobranzasScreen
import com.cipegas.administracion.presentation.OptionScreen
import com.cipegas.administracion.presentation.PrestamosScreen
import com.cipegas.administracion.presentation.ProviderScreen

@Composable
fun NavManager(navigationController: NavHostController) {
    NavHost(
        navController = navigationController,
        startDestination = Routes.Option.route
    ) {
        composable(Routes.Option.route) {
            OptionScreen(navigateTo = { optionId ->
                navigationController.navigate(Routes.Option.createOption(optionId))
            })
        }
        composable(Routes.Bank.route) {
            BankScreen(navigateToBack = { navigationController.popBackStack() })
        }
        composable(Routes.Loans.route) {
            PrestamosScreen()
        }
        composable(Routes.Chargue.route) {
            CobranzasScreen(
                navigateToBills = { id ->
                    navigationController.navigate(Routes.Bills.createRoute(id))
                },
                navigateToBack = {
                    navigationController.popBackStack()
                })
        }
        composable(Routes.Providers.route) {
            ProviderScreen()
        }
        composable(
            route = Routes.Bills.route,
            arguments = listOf(
                navArgument(name = "idClient") { type = NavType.IntType }
            )
        ) {
            BillScreen(
                idClient = it.arguments?.getInt("idClient") ?: 0,
                navigateToBack = {
                    navigationController.popBackStack()
                })

        }
    }
}

sealed class Routes(val route: String) {

    object Option : Routes("Option") {

        fun createOption(optionId: String): String {
            return optionId
        }

    }

    object Bank : Routes("Banks")
    object Loans : Routes("Loans")
    object Chargue : Routes("Chargue")
    object Providers : Routes("Providers")
    object Bills : Routes("Bills/{idClient}") {

        fun createRoute(idClient: Int) = "Bills/$idClient"
    }
}