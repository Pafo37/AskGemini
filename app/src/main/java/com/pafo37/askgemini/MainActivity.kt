package com.pafo37.askgemini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pafo37.askgemini.ui.screen.HomeScreen
import com.pafo37.askgemini.ui.theme.AskGeminiTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AskGeminiTheme {
                AskGeminiApp()
            }
        }
    }
}

@PreviewScreenSizes
@Composable
fun AskGeminiApp() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> { HomeScreen() }
    }
}

//TODO: move
@Serializable object Home

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AskGeminiTheme {

    }
}