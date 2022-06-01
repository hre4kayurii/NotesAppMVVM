package com.kawa.notesappmvvm.screens

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import com.kawa.notesappmvvm.navigation.NavRoute
import com.kawa.notesappmvvm.ui.theme.NotesAppMVVMTheme
import com.kawa.notesappmvvm.utils.*
import com.kawa.notesappmvvm.utils.Constants.Keys.ROOM_DATABASE
import com.kawa.notesappmvvm.utils.Constants.Keys.WHAT_WILL_WE_USE
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun StartScreen(navHostController: NavHostController, mViewModel: MainViewModel) {

    val bottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()

    var login by remember {
        mutableStateOf(Constants.Keys.EMPTY)
    }
    var password by remember {
        mutableStateOf(Constants.Keys.EMPTY)
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetState,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        sheetContent = {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 32.dp)
                ) {
                    Text(
                        text = Constants.Keys.LOG_IN,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    OutlinedTextField(
                        value = login,
                        onValueChange = { login = it },
                        label = { Text(text = Constants.Keys.LOGIN_TEXT) },
                        isError = login.isEmpty()
                    )
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(text = Constants.Keys.PASSWORD_TEXT) },
                        isError = password.isEmpty()
                    )

                    Button(
                        modifier = Modifier
                            .padding(top = 16.dp),
                        onClick = {
                            LOGIN = login
                            PASSWORD = password
                            mViewModel.initDatabase(TYPE_FIREBASE) {
                                DB_TYPE.value = TYPE_FIREBASE
                                Log.d("checkData", "Auth success")
                                navHostController.navigate(NavRoute.Main.route)
                            }
                        },
                        enabled = login.isNotEmpty() && password.isNotEmpty()
                    )
                    {
                        Text(text = Constants.Keys.SIGN_IN)
                    }

                }
            }
        }


    ) {

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

                Text(text = WHAT_WILL_WE_USE)

                Button(
                    onClick = {
                        mViewModel.initDatabase(TYPE_ROOM) {
                            DB_TYPE.value = TYPE_ROOM
                            navHostController.navigate(route = NavRoute.Main.route)
                        }
                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 8.dp)
                )
                {
                    Text(text = ROOM_DATABASE)
                }

                Button(
                    onClick = {
                        coroutineScope.launch {
                            bottomSheetState.show()
                        }

                    },
                    modifier = Modifier
                        .width(200.dp)
                        .padding(vertical = 8.dp)
                )
                {
                    Text(text = Constants.Keys.FIREBASE_DATABASE)
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrevStartScreen() {

    NotesAppMVVMTheme {
        val context = LocalContext.current
        val mViewModel: MainViewModel =
            viewModel(factory = MainViewModelFactory(context.applicationContext as Application))

        StartScreen(navHostController = rememberNavController(), mViewModel = mViewModel)

    }
}