package com.fuwumai.store

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.fuwumai.core.domain.User
import com.fuwumai.store.ui.theme.StoreTheme
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StoreTheme {
                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
                Navbar()
            }
        }
    }
}

@Composable
fun Navbar() {

    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(navController)
        }
        composable(
            route = "profile/{name}/{userId}/{timestamp}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                },
                navArgument("userId") {
                    type = NavType.StringType
                },
                navArgument("timestamp") {
                    type = NavType.LongType
                },
            )
        ) {
            val name = it.arguments?.getString("name")!!
            val userId = it.arguments?.getString("userId")!!
            val timestamp = it.arguments?.getLong("timestamp")!!

            ProfileScreen(
                navController = navController,
                name = name,
                userId = userId,
                created = timestamp
            )
        }
        composable("post/{showOnlyPostsByUser}", arguments = listOf(
            navArgument("showOnlyPostsByUser") {
                type = NavType.BoolType
                defaultValue = false
            }
        )) {
            val showOnlyPostsByUser =
                it.arguments?.getBoolean("showOnlyPostsByUser") ?: false
            PostScreen(showOnlyPostsByUser)
        }
    }
}

@Composable
fun LoginScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Login Screen")
        Button(onClick = {
            navController.navigate("profile/philipp/userid/123456789")
        }) {
            Text("Go to Profile Screen")
        }
    }
}

@Composable
fun ProfileScreen(
    navController: NavController,
    name: String,
    userId: String,
    created: Long
) {
    val user = remember {
        User(
            name = name,
            id = userId,
            created = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(created), ZoneId.systemDefault()
            )
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile Screen: $user", textAlign = TextAlign.Center)
        Button(onClick = {
            navController.navigate("post/true")
        }) {
            Text("Go to Post Screen")
        }
    }
}

@Composable
fun PostScreen(
    showOnlyPostsByUser: Boolean = false
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Post Screen, $showOnlyPostsByUser")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StoreTheme {
        LoginScreen(rememberNavController())
    }
}