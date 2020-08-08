package com.example.triviaapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triviaapp.base.BaseResult
import com.example.triviaapp.model.TriviaItem
import com.example.triviaapp.repository.TriviaRepository
import kotlinx.coroutines.launch

class DataViewModel : ViewModel() {

    var addTriviaLiveData = MutableLiveData<BaseResult<String>>()
    var listTriviaLiveData = MutableLiveData<BaseResult<List<TriviaItem>>>()

    /**
     * This method will insert data in local database(Room Db) via Repository
     * in 'trivia' table
     */
    fun insert(context: Context, triviaItem: TriviaItem) {
        viewModelScope.launch {
            val repository = TriviaRepository(context.applicationContext as Application)
            val response = repository.insert(triviaItem)
            if (response > 0) {
                addTriviaLiveData.value = BaseResult.Success("Data added successfully.")
            } else {
                addTriviaLiveData.value =
                    BaseResult.Error(IllegalStateException(), "Something went wrong.")
            }
        }
    }

    /**
     * This method will get list of Trivia items from local database(Room Db) via Repository
     * from 'trivia' table
     */
    fun getAllTrivia(context: Context) {
        viewModelScope.launch {
            val repository = TriviaRepository(context.applicationContext as Application)
            val response = repository.getAllTrivia()
            listTriviaLiveData.value = BaseResult.Success(response)
        }
    }
}