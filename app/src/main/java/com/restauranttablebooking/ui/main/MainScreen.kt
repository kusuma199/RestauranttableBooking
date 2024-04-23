package com.restauranttablebooking.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.restauranttablebooking.R
import com.restauranttablebooking.routing.Screen
import com.restauranttablebooking.ui.drawer.DrawerBody
import com.restauranttablebooking.ui.drawer.DrawerHeader
import com.restauranttablebooking.ui.drawer.TopBar
import com.restauranttablebooking.ui.model.RestaurantModel
import com.restauranttablebooking.ui.restaurantPreference.RestaurantPreference
import com.restauranttablebooking.ui.theme.RestaurantTableBookingAppTheme
import com.restauranttablebooking.ui.theme.black
import com.restauranttablebooking.ui.theme.white
import com.restauranttablebooking.utils.RoundedButton
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "MutableCollectionMutableState")
@Composable
fun MainScreen(navController: NavController) {
    val context = LocalContext.current
    val preferenceManager = remember {
        RestaurantPreference(context)
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    var isLogout by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()

    val list = ArrayList<RestaurantModel>().apply {
        add(RestaurantModel(id = "", image = "", name = "Test Restaurant", detail = "Test"))
        add(RestaurantModel(id = "", image = "", name = "Test Restaurant", detail = "Test"))
        add(RestaurantModel(id = "", image = "", name = "Test Restaurant", detail = "Test"))
        add(RestaurantModel(id = "", image = "", name = "Test Restaurant", detail = "Test"))
        add(RestaurantModel(id = "", image = "", name = "Test Restaurant", detail = "Test"))
        add(RestaurantModel(id = "", image = "", name = "Test Restaurant", detail = "Test"))
        add(RestaurantModel(id = "", image = "", name = "Test Restaurant", detail = "Test"))
        add(RestaurantModel(id = "", image = "", name = "Test Restaurant", detail = "Test"))
        add(RestaurantModel(id = "", image = "", name = "Test Restaurant", detail = "Test"))
        add(RestaurantModel(id = "", image = "", name = "Test Restaurant", detail = "Test"))
        add(RestaurantModel(id = "", image = "", name = "Test Restaurant", detail = "Test"))
    }

    RestaurantTableBookingAppTheme {
        androidx.compose.material.Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopBar(
                    navController = navController,
                    onNavigationIconClick = {
                        scope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }
                )
            },
            modifier = Modifier.background(color = black),
            drawerContent = {
                DrawerHeader()
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = white)
                ) {
                    DrawerBody(onLogout = {
                        isLogout = true
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    })
                }

            },
            backgroundColor = black,
            contentColor = black,
            drawerContentColor = white,
            drawerBackgroundColor = black
        ) { paddingValues ->
            Modifier.padding(
                bottom = paddingValues.calculateBottomPadding()
            )

            Column(modifier = Modifier
                .fillMaxSize()
                .background(white)) {
                Spacer(Modifier.height(10.dp))
                list.forEachIndexed { index, productModel ->
                    Card(
                        modifier = Modifier
                            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                            .fillMaxWidth()
                            .height(150.dp)
                            .clickable {
                                navController.navigate(Screen.DetailScreen.route)
                            },
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                    ) {

                        Image(
                            painter = painterResource(id = R.drawable.ic_restro_table),
                            contentDescription = "Image",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                        Text(
                            productModel.name ?: "",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 10.dp)
                        )

                    }
                }
            }
        }

        if (isLogout) {
            AlertDialog(
                onDismissRequest = {
                    isLogout = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("Are you sure you want to logout ?") },
                confirmButton = {
                    Row {
                        RoundedButton(
                            text = "Cancel",
                            textColor = white,
                            onClick = { isLogout = false }
                        )
                        Spacer(Modifier.height(10.dp))
                        RoundedButton(
                            text = "Logout",
                            textColor = white,
                            onClick = {
                                preferenceManager.saveData("isLogin", false)
                                navController.navigate(
                                    Screen.LoginScreen.route
                                ) {
                                    popUpTo(Screen.MainScreen.route) {
                                        inclusive = true
                                    }
                                }
                                isLogout = false
                            }
                        )
                    }

                },
                dismissButton = {}
            )
        }
    }


}

