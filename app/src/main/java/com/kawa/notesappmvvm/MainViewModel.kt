package com.kawa.notesappmvvm

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.kawa.notesappmvvm.database.firebase.AppFirebaseRepository
import com.kawa.notesappmvvm.database.room.AppRoomDatabase
import com.kawa.notesappmvvm.database.room.repository.RoomRepository
import com.kawa.notesappmvvm.model.Note
import com.kawa.notesappmvvm.utils.REPOSITORY
import com.kawa.notesappmvvm.utils.TYPE_FIREBASE
import com.kawa.notesappmvvm.utils.TYPE_ROOM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

            TYPE_FIREBASE -> {

                REPOSITORY = AppFirebaseRepository()
                REPOSITORY.connectToDatabase(
                    {onSuccess()},
                    {Log.d("checkData", "Error: $it")}
                )
            }
        }
    }


    fun addNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.create(note = note){
                viewModelScope.launch(Dispatchers.Main) {
                    onSuccess()
                }
            }
        }
    }

    fun updateNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.update(note){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }

    fun deleteNote(note: Note, onSuccess: () -> Unit){
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.delete(note){
                viewModelScope.launch(Dispatchers.Main){
                    onSuccess()
                }
            }
        }
    }
    fun readAllNotes() = REPOSITORY.readAll

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