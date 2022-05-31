package com.kawa.notesappmvvm.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.kawa.notesappmvvm.navigation.NavRoute
import com.kawa.notesappmvvm.ui.theme.NotesAppMVVMTheme

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navHostController: NavHostController) {

    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    //val notes = mViewModel.readTest.observeAsState(listOf()).value

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navHostController.navigate(NavRoute.Add.route) }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add",
                    tint = Color.White
                )
            }
        }
    ) {

       /* LazyColumn{
            items(notes){note ->
                NoteItem(
                    navHostController = navHostController,
                    note = note)
            }
        }*/
    }
}

@Composable
fun NoteItem(
    navHostController: NavHostController,
    note: Note
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 24.dp
            )
            .clickable {
                navHostController.navigate(NavRoute.Note.route)
            },
        elevation = 6.dp
    ) {

        Column(
            modifier =
            Modifier
                .padding(vertical = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = note.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = note.subtitle,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
fun PrevMainScreen() {

    NotesAppMVVMTheme {

        MainScreen(navHostController = rememberNavController())

    }
}