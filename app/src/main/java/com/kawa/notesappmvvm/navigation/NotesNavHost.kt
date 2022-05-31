package com.kawa.notesappmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kawa.notesappmvvm.MainViewModel
import com.kawa.notesappmvvm.screens.*

sealed class NavRoute(val route: String){
    object Start: NavRoute("start_screen")
    object Main: NavRoute("main_screen")
    object Add: NavRoute("add_screen")
    object Note: NavRoute("note_screen")
}

@Composable
fun NotesNavHost(mViewModel: MainViewModel) {

    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = NavRoute.Start.route){
        composable(NavRoute.Start.route){
            StartScreen(navController, mViewModel)
        }
        composable(NavRoute.Main.route){
            MainScreen(navController, mViewModel)
        }
        composable(NavRoute.Add.route){
            AddScreen(navController, mViewModel)
        }
        composable(NavRoute.Note.route){
            NoteScreen(navController, mViewModel)
        }
    }
}