package com.htam.coroutine

import android.util.Log
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import kotlinx.coroutines.delay

const val TAG = "Navigation"

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoutes.FirstScreen.route) {
        composable(route = ScreenRoutes.FirstScreen.route) {
            Greeting(navController)
        }
        composable(
            route = ScreenRoutes.SecondScreen.route + "/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Android"
                    nullable = true
                }
            )
        ) { entry ->
            Greeting2(name = entry.arguments?.getString("name"))
        }
    }
}

@Composable
fun Greeting(navController: NavController) {
    LaunchedEffect("") {
        delay(3000L)
        Log.d(TAG, "RUN IN GREETING")
    }
    Button(
        onClick = {
            navController.navigate(ScreenRoutes.SecondScreen.withArgs("htam"))
        }) {
        Text(text = "Click Me!!!")
    }
}

@Composable
fun Greeting2(name: String?) {
    LaunchedEffect("") {
        delay(3000L)
        Log.d(TAG, "RUN IN GREETING 2")
    }
    Text(text = "Hello from the second screen with argument: $name")
}