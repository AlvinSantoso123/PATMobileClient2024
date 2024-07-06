package com.example.patmobileclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.patmobileclient.userinterface.AddOrderScreen
import com.example.patmobileclient.userinterface.LoginScreen
import com.example.patmobileclient.userinterface.MenuScreen
import com.example.patmobileclient.userinterface.RegisterScreen
import com.example.patmobileclient.userinterface.ViewOrdersScreen

@Composable
fun NavGraph(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable("login") {
            LoginScreen(
                //navigateToMenu = { navController.navigate("menu") },
                navigateToMenu = { customerId -> navController.navigate("menu/$customerId") },
                navigateToRegister = { navController.navigate("register") }
            )
        }
        composable("register") {
            RegisterScreen(
                navigateBack = { navController.navigate("login") }
            )
        }
        composable("menu/{customerId}") { backStackEntry ->
            val customerId = backStackEntry.arguments?.getString("customerId")?.toInt() ?: 0
            MenuScreen(
                navigateToAddOrder = { navController.navigate("addOrder/$customerId") },
                navigateToViewOrders = { navController.navigate("viewOrders/$customerId") },
                customerId = customerId
            )
        }
        composable("addOrder/{customerId}") { backStackEntry ->
            val customerId = backStackEntry.arguments?.getString("customerId")?.toInt() ?: 0
            AddOrderScreen(
                customerId = customerId,
                navigateBack = { navController.popBackStack() }
            )
        }
        composable("viewOrders/{customerId}") { backStackEntry ->
            val customerId = backStackEntry.arguments?.getString("customerId")?.toInt() ?: 0
            ViewOrdersScreen(
                customerId = customerId,
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}