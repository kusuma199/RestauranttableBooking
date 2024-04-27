package com.restauranttablebooking.routing

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.restauranttablebooking.ui.bookScreen.BookScreen
import com.restauranttablebooking.ui.detail.DetailScreen
import com.restauranttablebooking.ui.feedback.FeedbackScreen
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
        composable(route = Screen.DetailScreen.route+ "/{name}"+"/{image}"+"/{address}"+"/{detail}") {
            val name = it.arguments?.getString("name")
            val image = it.arguments?.getString("image").toString().toInt()
            val address = it.arguments?.getString("address")
            val detail = it.arguments?.getString("detail")
            if (name != null) {
                if (image != null) {
                    if (address != null) {
                        if (detail != null) {
                            DetailScreen(navController = navController, name = name, image = image,detail = detail, address = address)
                        }
                    }
                }
            }
        }
        composable(route = Screen.BookScreen.route) {
            BookScreen(navController = navController)
        }
        composable(route = Screen.FeedbackScreen.route) {
            FeedbackScreen(navController = navController)
        }
        composable(route = Screen.ThankScreen.route) {
            ThankScreen(navController = navController)
        }

    }

}