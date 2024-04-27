package com.restauranttablebooking.ui.main

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
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
        add(RestaurantModel(id = "", image = R.drawable.ic_thai, name = "Thai Terminal 1", address = "Aviation Way, Darlington DL2 1PD England\n+44 1325 335978", detail = "If you’re looking for delicious, authentic Thai food, look no further than Thai Terminal 1 Restaurant. Conveniently located on the side of the Goosepool Hotel near Teesside Airport, we offer both sit-down dining and takeaway.\n" +
                "\n" +
                "Whether you’re in the mood for spicy, savory chicken and fresh vegetables or a fragrant bowl of noodle soup, our chefs are happy to please your palate with their traditional recipes and modern twists.\n" +
                "\n"))
        add(RestaurantModel(id = "", image = R.drawable.ic_rio, name = "Rio Brazilian Steakhouse", address = "93-101 Albert Road, Middlesbrough TS1 2PA England\n+44 1642 262288", detail = "Welcome to Rio Steakhouse!\n" +
                "\n" +
                "Let us take you on a culinary journey into the Tijuca Urban Forest that surrounds Rio de Janeiro, with planted walls and flowered ceilings towering high above a traditional Brazilian Churrasco dining experience!\n" +
                "\n" +
                "Our specially trained Gaucho Chefs are onhand to serve our exquisite meat cuts that are continuously carved at the table in a traditional Rodizio style."))
        add(RestaurantModel(id = "", image = R.drawable.ic_turtle, name = "Turtle Bay Middlesbrough", address = "32 Corporation Road, Middlesbrough TS1 2RX England\n+44 1642 246070", detail = "Heading to a gig or seeing your favourite show at the Middlesbrough Theatre or Middlesbrough Town Hall? Our Middlesbrough restaurant is just a 2-minute walk away! With most shows starting at 7.30pm, we totally understand the rush of squeezing in dinner...\n" +
                "\n" +
                "So, we've crafted the perfect set menu so you can sit back, relax and enjoy a tasty dinner and a couple of cocktails before showtime \uD83E\uDD17"))
        add(RestaurantModel(id = "", image = R.drawable.ic_pome, name = "Pomegranate Persian Tea Room", address = "#1of 13 Restaurants in Great AytonCafe,Middle Eastern,Persian\n5 Park Square, Great Ayton TS9 6BP England\n+44 1642 958764", detail = "Allergens– G – Gluten, N –Nuts, D – Dairy, E – Eggs, C – Crustacean, M – Molluscan, L – lupin, S – Sulphites, CEL – Celery, F – Fish, SES – Sesame, MUS – Mustard, P – Peanuts, SOY – Soy."))
        add(RestaurantModel(id = "", image = R.drawable.ic_rose, name = "Rose & Potter", address = "#1of 16 Restaurants in Marske-by-the-SeaItalian,British,Wine Bar\n98 High Street, Marske-by-the-Sea TS11 7BA England\n+44 7585 268721", detail = "From the germination of  an idea conceived at a Christmas market in Budapest, to the planning  and creation of our own piece of foodie heaven, right here in Marske-by-the-Sea,  it has certainly been a ride!\n" +
                "\n" +
                "A few micro bars were beginning to pop up in 2016/2017, but not many- and none that we knew of that shared our vision of a different kind of bar! We have matured and adapted to our customers’ preferences, but have never veered away from our vision of the highest quality products, ethical sourcing (checking/ creating provenance) in lovely, though casual surroundings… a cosy bar & bistro, not a restaurant.\n" +
                "\n" +
                "Six and a half years on, our gorgeous bar & deli is still the finest around for carefully selected and beautiful wines , including a range of from Cellar Espelt, suppliers to  the  famous Michelin starred, ‘El Bulli’  (a historical  reference for the foodies.. and wine lovers of you!).  Luckily for us, the UK Importer of Espelt wines lives locally, is a good friend, and has become  an integral part of the story of Rose and Potter! \n" +
                "\n" +
                "What better way to enjoy wine, than with some of our own high welfare,  ‘Cleveland Way Charcuterie’? The brand is registered to our ‘Rose & Potter’ name,  is carefully sourced from ethical and sustainable holdings, within the Cleveland Way/ North Yorkshire area., and cured in-house.\n" +
                "\n" +
                "Cleveland Way  Charcuterie’ and ‘Rose & Potter ‘ are synonymous with quality- in relaxed,  cosy & informal surroundings and although we can get incredibly busy at peak times, walk-ins, where there is space- or a visit for a quick round of cocktails/ beer etc. is very welcome!\n" +
                "\n" +
                "Check out our ‘News’, ‘Events’, ‘Party/Events and Shop, where you will be able to obtain extra informationand/or purchase special occasion platters"))
        add(RestaurantModel(id = "", image = R.drawable.ic_sandler, name = "Sadlers Cafe & Bistro", address = "#1of 22 Restaurants in Stokesley₹₹ - ₹₹₹,British,Contemporary,Vegetarian Friendly\n59-61 High Street, Stokesley TS9 5AB England\n+44 1642 956066", detail = "We are a family run Cafe and Bistro based in Stokesley, offering freshly cooked, quality food and drink\n" +
                "Welcome to our web page. We are a small, family run business located in the Market town of Stokesley in beautiful North Yorkshire. Sadlers is owned & managed by our resident chef Andrew Sadler with support from wife Jenny & Sister Sam. Our aim is to offer fine food & drink in a relaxed informal setting, from home baked Cakes & locally produced quality Coffee, through to Fine Dining options in our weekend Bistro."))
        add(RestaurantModel(id = "", image = R.drawable.ic_zetland, name = "The Zetland", address = "#4of 187 Restaurants in Middlesbrough₹₹ - ₹₹₹,British,Vegetarian Friendly,Vegan Options\n9 Zetland Road, Middlesbrough TS1 1EH England\n+44 1642 246777", detail = "The Zetland Hotel in Middlesbrough is a hidden Victorian gem. A year-long restoration was completed in the summer of 2018 and has seen the building restored to its former glory. The Zetland Hotel will provide bespoke hospitality for those wishing to enjoy its brasserie, a new-look bar or simply bask in the ambiance of one of Middlesbrough's most iconic buildings. \n" +
                "\n" +
                "Long regarded as one of the North East's most notable establishments, The Zetland Hotel has been painstakingly restored and adds a sense of character and charm that has long been missed in Middlesbrough's town centre. Built c. 1860, the Grade 2 Listed Building is noted for its special architecture and historic interest."))
        add(RestaurantModel(id = "", image = R.drawable.ic_porto, name = "Portofino", address = "#1of 114 Restaurants in Hartlepool₹₹ - ₹₹₹,Italian,Pizza,Bediterranean\nThe Historic Quay Maritime Avenue Maritime Avenue, Hartlepool TS24 0XF England\n+44 1429 266166", detail = "Portofino is a fishing village on the Italian Riviera coastline, southeast of Genoa city. Pastel-colored houses, high-end boutiques and seafood restaurants fringe its Piazzetta, a small cobbled square overlooking the harbor, which is lined with super-yachts. A path leads from the Piazzetta to Castello Brown, a 16th-century fortress and museum with art exhibitions and panoramic views of the town and the Ligurian Sea."))
        add(RestaurantModel(id = "", image = R.drawable.ic_glady, name = "Glady's Vintage Tea Room", address = "2of 114 Restaurants in Hartlepool₹₹ - ₹₹₹,Cafe,British,Vegetarian Friendly\n35 The Front Seaton Carew, Hartlepool TS25 1BS England\n+44 1429 861941", detail = "Now under new management since September 2023. Gladys Vintage Tea Room is a 1940s themed tearoom offering fresh homemade food. Our breakfasts are very popular and our Afternoon Teas are highly recommended. We also offer lite bites lunch menu and specialist teas and coffees. We look forward to seeing you so our Glady's troops can look after you and give you a warm welcoming experience. We have a portable ramp for any diabled access."))
        add(RestaurantModel(id = "", image = R.drawable.ic_cluck, name = "Cluck & Moo", address = "1of 67 Restaurants in Redcar₹₹ - ₹₹₹,Bar,British,Street Food\n3 Esplanade Redcar, Redcar TS10 3AA England\n+44 1642 483332", detail = "Welcome To Cluck & Moo Redcar’s first Rotisserie Bistro & Cocktail Bar. We are located In the heart of Redcar’s Northern Quarter. The ideal place to chill with friends & grab some awesome food or a few drinks.\n" +
                "\n" +
                "Redcar’s Northern Quarter is home too many exciting Indie bars, restaurants & boutiques. Cluck & Moo is ideally situated In the heart of this buzzing scene & is the perfect place for pre drinks or a bite to eat before, after or during that big night out.\n" +
                "\n"))
        add(RestaurantModel(id = "", image = R.drawable.ic_pepper, name = "The Pepper Mill Bistro", address = "1of 31 Restaurants in Billingham₹₹ - ₹₹₹,British,Vegetarian Friendly,Vegan Options\n42 Station Road, Stockton-on-Tees TS23 1AB England\n+44 1642 551934", detail = "Family run restaurant in Billingham working hard to bring you a fresh, seasonal & unique menu all in a modern, friendly & welcoming bistro environment.\n"))
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
                    DrawerBody(onRate = {
                        navController.navigate(Screen.FeedbackScreen.route)
                        scope.launch {
                            scaffoldState.drawerState.close()
                        }
                    },onLogout = {
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
                .background(white).verticalScroll(scrollState)) {
                Spacer(Modifier.height(10.dp))
                list.forEachIndexed { index, model ->
                    Card(
                        modifier = Modifier
                            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
                            .fillMaxWidth()
                            .height(IntrinsicSize.Min)
                            .clickable {
                                navController.navigate(Screen.DetailScreen.route + "/${model.name}" + "/${model.image}" + "/${model.address}"+ "/${model.detail}")
                            },
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
                    ) {

                        Image(
                            painter = painterResource(id = model.image),
                            contentDescription = "Image",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                        )
                        Text(
                            model.name ?: "",
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier
                                .padding(vertical = 5.dp, horizontal = 10.dp)
                        )
                        Text(
                            model.address ?: "",
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

                },
                dismissButton = {
                    RoundedButton(
                        text = "Cancel",
                        textColor = white,
                        onClick = { isLogout = false }
                    )
                }
            )
        }
    }


}

