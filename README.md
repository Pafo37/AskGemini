***

# AskGemini 🚀

An intelligent Android chat application built with the latest Kotlin 2.0 and Jetpack Compose, powered by Google's Gemini Pro model. AskGemini features persistent chat history, multi-chat management, and integrated speech-to-text capabilities.

## ✨ Features

- **AI-Powered Conversations**: Real-time interaction with Google Gemini Pro.
- **Persistent Chat History**: Locally stored conversations using Room Database.
- **Multi-Chat Support**: Easily switch between different discussion threads via a navigation drawer.
- **Speech-to-Text**: Hands-free questioning using integrated voice recognition.
- **Modern UI**: Built entirely with Jetpack Compose and Material 3 (including adaptive navigation).
- **Reactive Architecture**: State-driven UI with Kotlin Coroutines and Flow.

### Configuration

1. **Obtain an API Key**: Get your key from [Google AI Studio](https://aistudio.google.com/).
2. **Setup `local.properties`**: Add your key to the `local.properties` file in the root directory:
   ```properties
   GEMINI_API_KEY=YOUR_API_KEY_HERE
   ```
