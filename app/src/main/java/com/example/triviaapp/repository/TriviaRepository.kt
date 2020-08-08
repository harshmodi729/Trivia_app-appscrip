package com.example.triviaapp.repository

import android.app.Application
import com.example.triviaapp.data.dao.TriviaDao
import com.example.triviaapp.data.local.TriviaAppDatabase
import com.example.triviaapp.model.TriviaItem

class TriviaRepository(application: Application) {

    private var triviaDao: TriviaDao

    init {
        val triviaAppDatabase = TriviaAppDatabase.getInstance(application)
        triviaDao = triviaAppDatabase?.triviaDao!!
    }

    suspend fun insert(triviaItem: TriviaItem): Long {
        return triviaDao.insert(triviaItem)
    }

    suspend fun getAllTrivia(): List<TriviaItem> {
        return triviaDao.getAllTrivia()
    }
}