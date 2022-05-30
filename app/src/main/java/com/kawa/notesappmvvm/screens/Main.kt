package com.kawa.notesappmvvm.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kawa.notesappmvvm.navigation.NavRoute
import com.kawa.notesappmvvm.ui.theme.NotesAppMVVMTheme

@Composable
fun MainScreen(navHostController: NavHostController) {

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

        Column(
            modifier =
            Modifier.fillMaxSize()
        )
        {
            NoteItem(
                navHostController = navHostController,
                title = "Note 1",
                subtitle = "Subtitle for note 1"
            )
            NoteItem(
                navHostController = navHostController,
                title = "Note 1",
                subtitle = "Subtitle for note 1"
            )
            NoteItem(
                navHostController = navHostController,
                title = "Note 1",
                subtitle = "Subtitle for note 1"
            )
            NoteItem(
                navHostController = navHostController,
                title = "Note 1",
                subtitle = "Subtitle for note 1"
            )
        }


    }
}

@Composable
fun NoteItem(
    navHostController: NavHostController,
    title: String,
    subtitle: String
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
                text = title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = subtitle,
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