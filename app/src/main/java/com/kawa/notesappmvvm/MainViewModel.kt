package com.kawa.notesappmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kawa.notesappmvvm.database.room.AppRoomDatabase
import com.kawa.notesappmvvm.database.room.repository.RoomRepository
import com.kawa.notesappmvvm.model.Note
import com.kawa.notesappmvvm.utils.REPOSITORY
import com.kawa.notesappmvvm.utils.TYPE_FIREBASE
import com.kawa.notesappmvvm.utils.TYPE_ROOM

class MainViewModel(application: Application) :
    AndroidViewModel(application) {



    val context = application

    fun initDatabase(type: String, onSuccess: () -> Unit) {

        Log.d("checkData", "MainViewModel initDatabase: $type")

        when (type) {
            TYPE_ROOM -> {
                val dao = AppRoomDatabase.getInstance(context = context).getRoomDao()
                REPOSITORY = RoomRepository(dao)
                onSuccess()
            }
        }
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