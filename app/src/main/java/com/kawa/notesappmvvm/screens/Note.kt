package com.kawa.notesappmvvm.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kawa.notesappmvvm.MainViewModel
import com.kawa.notesappmvvm.MainViewModelFactory
import com.kawa.notesappmvvm.model.Note
import com.kawa.notesappmvvm.ui.theme.NotesAppMVVMTheme
import com.kawa.notesappmvvm.utils.Constants
import com.kawa.notesappmvvm.utils.Constants.Keys.NONE
import com.kawa.notesappmvvm.utils.Constants.Keys.SUBTITLE
import com.kawa.notesappmvvm.utils.Constants.Keys.TITLE

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteScreen(
    navHostController: NavHostController,
    mViewModel: MainViewModel,
    noteId: String?) {

    val notes = mViewModel.readAllNotes().observeAsState(listOf()).value
    val note = notes.firstOrNull{ it.id == noteId?.toInt()} ?: Note(title = NONE, subtitle = NONE)

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = note.title,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 32.dp)
                    )
                    Text(text = note.subtitle,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        modifier = Modifier.padding(top = 16.dp))
                }
            }

            Row(
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Button(onClick = {
                    
                }) {
                    Text(text = Constants.Keys.UPDATE)
                }
                Button(onClick = {

                }) {
                    Text(text = Constants.Keys.DELETE)
                }
            }
            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .padding(horizontal = 32.dp)
                    .fillMaxWidth(),
                onClick = {

            }) {
                Text(text = Constants.Keys.DELETE)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PrevNoteScreen() {

    NotesAppMVVMTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

        NoteScreen(
            navHostController = rememberNavController(),
            mViewModel = mViewModel,
            noteId = "1"
        )

    }
}