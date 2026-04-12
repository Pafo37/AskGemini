package com.pafo37.askgemini.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.pafo37.askgemini.model.entity.ChatEntity
import com.pafo37.askgemini.model.entity.MessageEntity

@Database(entities = [ChatEntity::class, MessageEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun chatDao(): ChatDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gemini_chat_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}