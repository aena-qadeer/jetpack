package com.example.jetpacknavigation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.jetpacknavigation.ui.theme.JetpackNavigationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackNavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    Log.d("TAG", "onCreate: $it")
                    MyNavigation()
                }
            }
        }
    }
}

@Composable
fun MyNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "MainPage") {

        composable(route = "MainPage") {
            MainPage(navController)
        }

        composable(route = "SecondPage/{name}", arguments = listOf(navArgument("name"){type= NavType.StringType })) { navBackStackEntry->
            val name = navBackStackEntry.arguments?.getString("name")
            name?.let{
                SecondPage(navController,name)
            }


        }
    }
}

