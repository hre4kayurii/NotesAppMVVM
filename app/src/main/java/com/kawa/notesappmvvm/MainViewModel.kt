package com.kawa.notesappmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kawa.notesappmvvm.model.Note
import com.kawa.notesappmvvm.utils.TYPE_FIREBASE
import com.kawa.notesappmvvm.utils.TYPE_ROOM

class MainViewModel(application: Application) :
    AndroidViewModel(application) {

    val readTest: MutableLiveData<List<Note>> by lazy {
        MutableLiveData<List<Note>>()
    }

    val dbType: MutableLiveData<String> by lazy {
        MutableLiveData<String>(TYPE_ROOM)
    }

    init {
        readTest.value =
            when(dbType.value){
                TYPE_ROOM -> {
                    listOf<Note>(
                        Note(title = "note 1", subtitle = "subtitle note 1"),
                        Note(title = "note 2", subtitle = "subtitle note 2"),
                        Note(title = "note 3", subtitle = "subtitle note 3"),
                        Note(title = "note 4", subtitle = "subtitle note 4"),
                    )
                }
                TYPE_FIREBASE -> {
                    listOf()
                }
                else -> {
                    listOf()
                }
            }
    }

    fun initDatabase(type: String) {
        dbType.value = type
        Log.d("checkData", "MainViewModel initDatabase: $type")
    }

}

class MainViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(application = application) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}