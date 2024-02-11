@file:OptIn(ExperimentalMaterial3Api::class)

package com.xs.rolladice

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.xs.rolladice.ui.historypage.HistoryRollScreen
import com.xs.rolladice.ui.homepage.HomePageScreen
import com.xs.rolladice.ui.namepage.NamePageScreen
import com.xs.rolladice.ui.splashScreen.SplashScreen
import com.xs.rolladice.ui.theme.MVVMRollADiceAppTheme
import com.xs.rolladice.utils.ROUTES
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MVVMRollADiceAppTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = ROUTES.Splash_Screen) {
                    composable(ROUTES.Splash_Screen) {
                        SplashScreen {
                            navController.navigate(
                                it.route,
                                navOptions = NavOptions.Builder()
                                    .setPopUpTo(ROUTES.Splash_Screen, true).build()
                            )
                        }
                    }
                    composable(ROUTES.Change_Name) {
                        NamePageScreen(onNavigate = {
                            navController.navigate(it.route)
                        })


                    }
                    //Todo: Here is NavArg Composable in Navigation
                    composable(ROUTES.Home_Page + "?name={name}",
                        arguments = listOf(navArgument(name = "name") {
                            type = NavType.StringType
                            defaultValue = "Guest"
                        })
                    ) {
                        HomePageScreen(onNavigate = {
                            navController.navigate(it.route)
                        }, onPopBackStack = {
                            navController.popBackStack()
                        })
                    }

                    composable(
                        ROUTES.View_History + "?name={name}",
                        arguments = listOf(navArgument(name = "name"){
                        type = NavType.StringType
                        defaultValue = "Zain Sherazi"
                    })) {
                        HistoryRollScreen()
                    }


                }
            }
        }
    }
}


