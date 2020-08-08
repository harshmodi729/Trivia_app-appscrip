package com.example.triviaapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.triviaapp.model.TriviaItem

@Dao
interface TriviaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(triviaItem: TriviaItem): Long

    @Query("SELECT * FROM trivia ORDER BY id DESC")
    suspend fun getAllTrivia(): List<TriviaItem>
}