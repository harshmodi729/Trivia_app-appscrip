package com.example.triviaapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.triviaapp.data.dao.TriviaDao
import com.example.triviaapp.model.TriviaItem

@Database(entities = [TriviaItem::class], version = 1)
abstract class TriviaAppDatabase : RoomDatabase() {

    abstract val triviaDao: TriviaDao

    companion object {
        private var instance: TriviaAppDatabase? = null

        /**
         * this method will create singleton instance of the local
         * app database
         */
        @Synchronized
        fun getInstance(context: Context): TriviaAppDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    TriviaAppDatabase::class.java,
                    "trivia_app"
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }
}