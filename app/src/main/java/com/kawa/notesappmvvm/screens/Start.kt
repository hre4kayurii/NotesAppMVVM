package com.kawa.notesappmvvm.screens

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kawa.notesappmvvm.MainViewModel
import com.kawa.notesappmvvm.MainViewModelFactory
import com.kawa.notesappmvvm.navigation.NavRoute
import com.kawa.notesappmvvm.ui.theme.NotesAppMVVMTheme
import com.kawa.notesappmvvm.utils.TYPE_DATABASE
import com.kawa.notesappmvvm.utils.TYPE_FIREBASE
import com.kawa.notesappmvvm.utils.TYPE_ROOM

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StartScreen(navHostController: NavHostController) {

    val context = LocalContext.current
    val mViewModel: MainViewModel =
        viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(text = "What will we use?")

            Button(
                onClick = {
                    mViewModel.initDatabase(TYPE_ROOM) {
                        navHostController.navigate(route = NavRoute.Main.route)
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            )
            {
                Text(text = "Room database")
            }

            Button(
                onClick = {
                    mViewModel.initDatabase(TYPE_FIREBASE) {
                        navHostController.navigate(route = NavRoute.Main.route)
                    }
                },
                modifier = Modifier
                    .width(200.dp)
                    .padding(vertical = 8.dp)
            )
            {
                Text(text = "Firebase database")
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevStartScreen() {

    NotesAppMVVMTheme {

        StartScreen(navHostController = rememberNavController())

    }
}