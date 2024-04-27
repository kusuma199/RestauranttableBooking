package com.restauranttablebooking.ui.register

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.restauranttablebooking.R
import com.restauranttablebooking.routing.Screen
import com.restauranttablebooking.ui.restaurantPreference.RestaurantPreference
import com.restauranttablebooking.ui.theme.black
import com.restauranttablebooking.ui.theme.white
import com.restauranttablebooking.utils.RestaurantTableBookingBorderFeild
import com.restauranttablebooking.utils.RoundedButton
import com.restauranttablebooking.utils.isValidEmail

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavController) {
    val context = LocalContext.current
    val preference = remember {
        RestaurantPreference(context)
    }
    var isUser by remember { mutableStateOf(false) }
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val firebaseAuth = FirebaseAuth.getInstance()
    Scaffold {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(black)
        ) {
            Spacer(
                modifier = Modifier
                    .background(black)
                    .height(45.dp)
            )
            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.ic_restro_table),
                    contentDescription = "",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(white)
                    .padding(15.dp)
            ) {
                Text(
                    "Welcome To Restaurant Table Booking App\nRegister To Continue",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(color = black, fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Name",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(color = black)
                )
                Spacer(modifier = Modifier.height(10.dp))
                RestaurantTableBookingBorderFeild(
                    value = name,
                    onValueChange = { text ->
                        name = text
                    },
                    placeholder = "Enter name",
                    keyboardType = KeyboardType.Text,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Email",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(color = black)
                )
                Spacer(modifier = Modifier.height(10.dp))
                RestaurantTableBookingBorderFeild(
                    value = email,
                    onValueChange = { text ->
                        email = text
                    },
                    placeholder = "Enter email",
                    keyboardType = KeyboardType.Email,
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    "Password",
                    modifier = Modifier.fillMaxWidth(),
                    style = TextStyle(color = black)
                )
                Spacer(modifier = Modifier.height(10.dp))
                RestaurantTableBookingBorderFeild(
                    value = password,
                    onValueChange = { text ->
                        password = text
                    },
                    placeholder = "Enter Password",
                    keyboardType = KeyboardType.Password,
                    visualTransformation = PasswordVisualTransformation(),
                )



                Spacer(modifier = Modifier.height(30.dp))
                Row(
                    modifier = Modifier
                ) {
                    RoundedButton(
                        text = "Register",
                        textColor = white,
                        onClick = {
                            if (name.isNotEmpty()) {
                                if (email.isNotEmpty()) {
                                    if (!isValidEmail(email)) {
                                        if (password.isNotEmpty()) {
                                            isUser = true
                                            firebaseAuth.createUserWithEmailAndPassword(
                                                email.lowercase(),
                                                password
                                            )
                                                .addOnCompleteListener { task ->
                                                    if (task.isSuccessful) {
                                                        preference.saveData(
                                                            "isLogin", true
                                                        )
                                                        Toast.makeText(
                                                            context,
                                                            "Register successfully.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        navController.navigate(
                                                            Screen.MainScreen.route
                                                        ) {
                                                            popUpTo(Screen.RegisterScreen.route) {
                                                                inclusive = true
                                                            }
                                                        }
                                                        isUser = false
                                                    } else {
                                                        Toast.makeText(
                                                            context,
                                                            task.exception?.message.toString(),
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        isUser = false
                                                    }
                                                }
                                        } else {
                                            Toast.makeText(
                                                context,
                                                "Please enter password.",
                                                Toast.LENGTH_LONG
                                            ).show()

                                        }
                                    } else {
                                        Toast.makeText(
                                            context,
                                            "Please enter email.",
                                            Toast.LENGTH_LONG
                                        ).show()

                                    }
                                } else {
                                    Toast.makeText(
                                        context,
                                        "Please enter valid email.",
                                        Toast.LENGTH_LONG
                                    ).show()

                                }
                            } else {
                                Toast.makeText(
                                    context,
                                    "Please enter name.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    )
                }
            }


        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Already have an account?",
                    textAlign = TextAlign.End,
                    style = TextStyle(color = black)
                )

                Text(
                    " Login", modifier = Modifier.clickable {
                        navController.navigate(Screen.LoginScreen.route) {
                            popUpTo(Screen.RegisterScreen.route) {
                                inclusive = true
                            }
                        }
                    }, textAlign = TextAlign.End,
                    style = TextStyle(color = black)
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
        if (isUser) {
            Dialog(
                onDismissRequest = { },
                DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .size(100.dp)
                        .background(white, shape = RoundedCornerShape(8.dp))
                ) {
                    CircularProgressIndicator(color = black)
                }
            }
        }
    }
}