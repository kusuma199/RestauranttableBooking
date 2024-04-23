package com.restauranttablebooking.ui.thankScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.restauranttablebooking.R
import com.restauranttablebooking.routing.Screen
import com.restauranttablebooking.ui.restaurantPreference.RestaurantPreference
import com.restauranttablebooking.ui.theme.RestaurantTableBookingAppTheme
import com.restauranttablebooking.ui.theme.black
import com.restauranttablebooking.ui.theme.white
import com.restauranttablebooking.utils.RoundedButton
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ThankScreen(navController: NavController) {

    RestaurantTableBookingAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .background(color = black)
                    .padding(top = 40.dp)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Thanks Screen", color = Color.White,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.navigateUp()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.ArrowBack,
                                tint = Color.White,
                                contentDescription = "Back"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = black,
                        titleContentColor = Color.White
                    )
                )

                Column(modifier = Modifier.background(color = white)) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_restro_table),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Text(
                        "Thank you!!!\nYour table has been booked successfully.",
                        fontSize = 14.sp,
                        color = Color.Black,
                        modifier = Modifier
                            .padding(vertical = 5.dp, horizontal = 10.dp)
                    )
                    Row(
                        modifier = Modifier
                    ) {
                        RoundedButton(
                            text = "Continue",
                            textColor = white,
                            onClick = {
                                navController.navigate(
                                    Screen.MainScreen.route
                                ) {
                                    popUpTo(Screen.ThankScreen.route) {
                                        inclusive = true
                                    }
                                }
                            })
                    }
                }
            }
        }
    }
}