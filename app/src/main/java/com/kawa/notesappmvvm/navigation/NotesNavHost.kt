package com.kawa.notesappmvvm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kawa.notesappmvvm.MainViewModel
import com.kawa.notesappmvvm.screens.*
import com.kawa.notesappmvvm.utils.Constants

sealed class NavRoute(val route: String){
    object Start: NavRoute(Constants.Screens.START_SCREEN)
    object Main: NavRoute(Constants.Screens.MAIN_SCREEN)
    object Add: NavRoute(Constants.Screens.ADD_SCREEN)
    object Note: NavRoute(Constants.Screens.NOTE_SCREEN)
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
        composable(NavRoute.Note.route + "/{${Constants.Keys.ID}}"){
            backStackEntry ->
            NoteScreen(navHostController = navController,
                mViewModel = mViewModel,
                noteId = backStackEntry.arguments?.getString(Constants.Keys.ID))
        }
    }
}