package com.example.triviaapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trivia")
class TriviaItem {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var name: String = ""
    var bestCricketer: String = ""
    var colors: String = ""
    var time: String = ""
}