package com.restauranttablebooking.ui.bookScreen

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavController
import com.restauranttablebooking.R
import com.restauranttablebooking.routing.Screen
import com.restauranttablebooking.ui.theme.RestaurantTableBookingAppTheme
import com.restauranttablebooking.ui.theme.black
import com.restauranttablebooking.ui.theme.white
import com.restauranttablebooking.utils.RestaurantTableBookingBorderFeild
import com.restauranttablebooking.utils.RoundedButton
import java.util.*

@OptIn(
    ExperimentalMaterial3Api::class
)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BookScreen(navController: NavController) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    var isSubmit by remember {
        mutableStateOf(false)
    }
    var name by remember { mutableStateOf("") }
    var mobile by remember { mutableStateOf("") }

    var selectedDate by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }

    var selectTime by remember { mutableStateOf("") }
    var showTimePicker by remember { mutableStateOf(false) }

    var isType by rememberSaveable {
        mutableStateOf(false)
    }
    val type = arrayListOf(
        "Dinner", "VIP/Mezzanine",
        "Birthday/Anniversary", "Weeding",
        "Holiday", "Other"
    )
    var selectedType by remember { mutableStateOf("") }
    RestaurantTableBookingAppTheme {
        Scaffold {
            Column(
                modifier = Modifier
                    .background(color = black)
                    .padding(top = 40.dp)
                    .verticalScroll(scrollState)
            ) {
                SmallTopAppBar(
                    title = {
                        Text(
                            text = "Submit Booking", color = Color.White,
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

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.background(color = white).padding(start = 10.dp,end = 10.dp)
                ) {

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
                        "Mobile",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = black)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    RestaurantTableBookingBorderFeild(
                        value = mobile,
                        onValueChange = { text ->
                            mobile = text
                        },
                        placeholder = "Enter mobile",
                        keyboardType = KeyboardType.Phone,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Date",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = black)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    RestaurantTableBookingBorderFeild(
                        value = if (selectedDate != "") selectedDate else "Select Date",
                        onValueChange = { text ->
                            selectedDate = text
                        },
                        onClick = {
                            showDatePicker = true
                        },
                        isEnabled = false,
                        placeholder = "Select Date"
                    )
                    if (showDatePicker) {
                        context.DatePickerDialogBox(onDateSelect = {
                            selectedDate = it
                            showDatePicker = false
                        })
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Time" +
                                "",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = black)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    RestaurantTableBookingBorderFeild(
                        value = if (selectTime != "") selectTime else "Select Time",
                        onValueChange = { text ->
                            selectTime = text
                        },
                        onClick = {
                            showTimePicker = true
                        },
                        isEnabled = false,
                        placeholder = "Select Time"
                    )
                    if (showTimePicker) {
                        context.TimePickerDialogBox {
                            selectTime = it
                            showTimePicker = false
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        "Type",
                        modifier = Modifier.fillMaxWidth(),
                        style = TextStyle(color = black)
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    RestaurantTableBookingBorderFeild(
                        value = if (selectedType != "") selectedType else "Select Type",
                        onValueChange = { text ->
                            selectedType = text
                        },
                        onClick = {
                            isType = true
                        },
                        isEnabled = false,
                        placeholder = "Select Type"
                    )
                    Box {
                        if (isType) {
                            Popup(
                                alignment = Alignment.TopCenter,
                                properties = PopupProperties(
                                    excludeFromSystemGesture = true,
                                ),
                                onDismissRequest = { isType = false }
                            ) {

                                Column(
                                    modifier = Modifier
                                        .verticalScroll(state = scrollState)
                                        .border(width = 1.dp, color = Color.Gray)
                                        .background(white),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {

                                    type.onEachIndexed { index, item ->
                                        if (index != 0) {
                                            Divider(thickness = 1.dp, color = Color.LightGray)
                                        }
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp)
                                                .clickable {
                                                    selectedType = item
                                                    isType = !isType
                                                },
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(text = item, style = TextStyle(color = black))
                                        }
                                    }

                                }
                            }
                        }
                    }
                    Spacer(Modifier.height(30.dp))
                    Row(
                        modifier = Modifier
                    ) {
                        RoundedButton(
                            text = "Submit Booking",
                            textColor = white,
                            onClick = {
                                if(name.isEmpty()) {
                                    Toast.makeText(context, "Please enter name.", Toast.LENGTH_SHORT).show()
                                } else if(mobile.isEmpty()) {
                                    Toast.makeText(context, "Please enter mobile.", Toast.LENGTH_SHORT).show()
                                }  else if(mobile.length<10) {
                                    Toast.makeText(context, "Please enter valid mobile.", Toast.LENGTH_SHORT).show()
                                } else if(selectedDate.isEmpty()) {
                                    Toast.makeText(context, "Please select date.", Toast.LENGTH_SHORT).show()
                                } else if(selectedDate.isEmpty()) {
                                    Toast.makeText(context, "Please select date.", Toast.LENGTH_SHORT).show()
                                } else if(selectedType.isEmpty()) {
                                    Toast.makeText(context, "Please select type.", Toast.LENGTH_SHORT).show()
                                } else {
                                    isSubmit = true
                                }

                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }
        if (isSubmit) {
            AlertDialog(
                onDismissRequest = {
                    isSubmit = false
                },
                title = { Text(stringResource(id = R.string.app_name)) },
                text = { Text("your table has been successfully booked.") },
                confirmButton = {
                    RoundedButton(
                        text = "Ok",
                        textColor = white,
                        onClick = {
                            navController.navigate(Screen.ThankScreen.route)
                            isSubmit = false
                        }
                    )
                },
                dismissButton = {}
            )
        }


    }
}

@Composable
fun Context.DatePickerDialogBox(
    onDateSelect: (String) -> Unit
) {
    val year: Int
    val month: Int
    val day: Int
    val mCalendar = Calendar.getInstance()
    year = mCalendar.get(Calendar.YEAR)
    month = mCalendar.get(Calendar.MONTH)
    day = mCalendar.get(Calendar.DAY_OF_MONTH)

    mCalendar.time = Date()
    val mDatePickerDialog = DatePickerDialog(
        this,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            onDateSelect("$mDayOfMonth/${mMonth + 1}/$mYear")
        }, year, month, day
    )
    mDatePickerDialog.show()
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Context.TimePickerDialogBox(
    onTimeSelect: (String) -> Unit
) {
    val mCalendar = Calendar.getInstance()
    val hour = mCalendar[Calendar.HOUR_OF_DAY]
    val minute = mCalendar[Calendar.MINUTE]
    val mTimePickerDialog = TimePickerDialog(
        this,
        {_, mHour : Int, mMinute: Int ->
            onTimeSelect("$mHour:$mMinute")
        }, hour, minute, false
    )
    mTimePickerDialog.show()
}