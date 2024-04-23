package com.restauranttablebooking.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.restauranttablebooking.ui.bookScreen.BookScreen
import com.restauranttablebooking.ui.detail.DetailScreen
import com.restauranttablebooking.ui.login.LoginScreen
import com.restauranttablebooking.ui.main.MainScreen
import com.restauranttablebooking.ui.register.RegisterScreen
import com.restauranttablebooking.ui.splash.SplashScreen
import com.restauranttablebooking.ui.thankScreen.ThankScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            SplashScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.DetailScreen.route) {
            DetailScreen(navController = navController)
        }
        composable(route = Screen.BookScreen.route) {
            BookScreen(navController = navController)
        }
        composable(route = Screen.ThankScreen.route) {
            ThankScreen(navController = navController)
        }

    }

}