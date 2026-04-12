package com.pafo37.askgemini.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.pafo37.askgemini.BuildConfig
import com.pafo37.askgemini.model.Message
import com.pafo37.askgemini.model.entity.ChatEntity
import com.pafo37.askgemini.model.entity.MessageEntity
import com.pafo37.askgemini.room.ChatDao
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel(private val dao: ChatDao) : ViewModel() {

    val generativeModel = GenerativeModel(
        modelName = "gemini-2.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    var chatSession = generativeModel.startChat()
    val allChats = dao.getAllChats()

    val messages = mutableStateListOf<Message>()

    var isLoading by mutableStateOf(false)
        private set

    var currentChatId by mutableStateOf<Long?>(null)
    fun sendMessage(userText: String) {
        viewModelScope.launch {
            val chatIdToUse = currentChatId ?: createNewChat(userText)

            val userMessage = MessageEntity(
                chatId = chatIdToUse,
                text = userText,
                isFromUser = true
            )
            dao.insertMessage(userMessage)

            if (chatIdToUse == currentChatId || currentChatId == null) {
                messages.add(Message(userText, isFromUser = true))
            }

            isLoading = true

            try {
                val response = chatSession.sendMessage(userText)

                response.text?.let { botResponse ->
                    dao.insertMessage(
                        MessageEntity(chatId = chatIdToUse, text = botResponse, isFromUser = false)
                    )

                    if (chatIdToUse == currentChatId) {
                        messages.add(Message(botResponse, isFromUser = false))
                    }
                }
            } catch (e: Exception) {
                if (chatIdToUse == currentChatId) {
                    messages.add(Message("Error: ${e.message}", false))
                }
            } finally {
                isLoading = false
            }
        }
    }

    private suspend fun createNewChat(firstMessage: String): Long {
        val title = if (firstMessage.length > 20) firstMessage.take(20) + "..." else firstMessage
        val newId = dao.insertChat(ChatEntity(title = title))
        currentChatId = newId
        return newId
    }

    fun loadChat(id: Long) {
        currentChatId = id
        messages.clear()

        viewModelScope.launch {
            val history = dao.getMessagesForChat(id).first()

            chatSession = generativeModel.startChat(
                history = history.map { entity ->
                    content(role = if (entity.isFromUser) "user" else "model") {
                        text(entity.text)
                    }
                }
            )

            messages.addAll(history.map { Message(it.text, it.isFromUser) })
        }
    }

    fun startNewChat() {
        messages.clear()
        currentChatId = null
        chatSession = generativeModel.startChat()
    }
}