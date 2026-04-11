package com.pafo37.askgemini.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.pafo37.askgemini.model.Message
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    //TODO: move somewhere safer
    val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = "AIzaSyBOCZRfUR7-oBbHf0Rj7AuKBr8l75_LXOE"
    )

    val messages = mutableStateListOf<Message>()

    var isLoading by mutableStateOf(false)
        private set

    fun sendMessage(userText: String) {
        messages.add(Message(userText, isFromUser = true))
        isLoading = true

        viewModelScope.launch {
            val response = generativeModel.generateContent(userText)
            response.text?.let { messages.add(Message(it, isFromUser = false)) }
            isLoading = false
        }
    }
}