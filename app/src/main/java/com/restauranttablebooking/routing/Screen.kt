package com.restauranttablebooking.routing

sealed class Screen(val route: String) {

    object SplashScreen: Screen("splash_screen")
    object LoginScreen: Screen("login_screen")
    object RegisterScreen: Screen("register_screen")
    object MainScreen: Screen("main_screen")
    object DetailScreen: Screen("detail_screen")
    object BookScreen: Screen("book_screen")
    object ThankScreen: Screen("thank_screen")
    object FeedbackScreen: Screen("feedback_screen")

}