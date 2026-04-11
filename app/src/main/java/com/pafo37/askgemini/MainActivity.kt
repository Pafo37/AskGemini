package com.pafo37.askgemini

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pafo37.askgemini.ui.screen.HomeScreen
import com.pafo37.askgemini.ui.theme.AskGeminiTheme
import com.pafo37.askgemini.viewmodel.HomeViewModel

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
        composable<Home> {
            val viewModel: HomeViewModel = viewModel()
            HomeScreen(
                messages = viewModel.messages,
                isLoading = viewModel.isLoading,
                onSendMessage = { text -> viewModel.sendMessage(text) }
            )
        }
    }
}