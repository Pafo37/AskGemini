package com.pafo37.askgemini.model

data class Message(
    val text: String,
    val isFromUser: Boolean,
    val hasError: Boolean = false
)
