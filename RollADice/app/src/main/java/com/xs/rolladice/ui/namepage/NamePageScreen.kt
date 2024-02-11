@file:OptIn(ExperimentalMaterial3Api::class)

package com.xs.rolladice.ui.namepage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Create
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.xs.rolladice.R
import com.xs.rolladice.ui.MySnackBar
import com.xs.rolladice.ui.homepage.DiceColoredText
import com.xs.rolladice.ui.homepage.MyTopBar
import com.xs.rolladice.utils.ROUTES
import com.xs.rolladice.utils.UiEvent
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterial3Api
@Composable
fun NamePageScreen(
    viewModel: NamePageViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
) {
    val namesList = viewModel.names.collectAsState(initial = emptyList())

    val snackbarHostState = remember { SnackbarHostState() }


    //This do Launched Effect Stuff for UiEvents Only Mostly I think
    LaunchedEffect(key1 = true, block = {
        viewModel.uiEvent.collectLatest {
            when (it) {
                is UiEvent.Navigate -> onNavigate(it)
                UiEvent.PopBackStack -> {
                    //Not Needed Here
                }

                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        it.msg, it.action,
                        duration = SnackbarDuration.Short
                    )
                }

                UiEvent.ShowDialogBox -> {
                    viewModel.onDialogPopup()
                }
            }
        }
    })


    Scaffold(
        topBar = { MyTopBar(title = "Add/Edit Names", icon = Icons.TwoTone.Create) },
        snackbarHost = {
            MySnackBar(
                snackBarHostState = snackbarHostState,
                onUndoDelete = { viewModel.onUndoDelete() })
        }
    ) {

        Column(
            modifier = Modifier
                .padding(it)
                .animateContentSize(
                    spring(Spring.DampingRatioMediumBouncy, Spring.StiffnessMedium)
                )
                .background(color = Color.White)
                .fillMaxSize(),
            verticalArrangement = viewModel.definesArrangement(),
            Alignment.CenterHorizontally
        ) {
            /**
             * THE TWO MAIN BUTTON COME HERE
             */
            AnimatedVisibility(visible = true) {
                TwoButtons(
                    showPopUp = { viewModel.onDialogPopup() },
                    viewNames = {
                        viewModel.viewNamesList(!viewModel.showNameList)
                    }
                )
            }


            /**
             * Lazy Column Begins Here
             */
            AnimatedVisibility(
                viewModel.showNameList,
                enter = expandVertically(
                    animationSpec = spring(
                        Spring.DampingRatioMediumBouncy,
                        Spring.StiffnessLow
                    )
                ),
                exit = shrinkVertically(
                    animationSpec = spring(
                        Spring.DampingRatioNoBouncy,
                        Spring.StiffnessMediumLow
                    )
                )
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .background(color = Color.White)
                        .animateContentSize(
                            animationSpec = spring(
                                Spring.DampingRatioMediumBouncy,
                                Spring.StiffnessMedium
                            )
                        )
                ) {
                    items(namesList.value) { name ->
                        NameItem(
                            modifier = Modifier.clickable {
                                //Todo: Here Nav Args are Sent
                                viewModel.sendUiEvent(UiEvent.Navigate(ROUTES.Home_Page + "?name=${name.name}"))
                            },
                            name = name,
                            onDeleteName = {
                                viewModel.onDeleteName(name)
                            },
                            onShowPopUp = { viewModel.onDialogEditPop(name) },
                        )
                    }


                }
            }


        }

        //THE POPUP
        if (viewModel.showDialog) {
            Dialog(onDismissRequest = {
                viewModel.onDialogDismiss()
            }) {
                Card(
                    modifier = Modifier,
                ) {
                    Column {
                        TextField(
                            value = viewModel.nameField,
                            onValueChange = { newValue ->
                                viewModel.onNameFieldChanged(newValue)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                                .background(color = Color.White),
                            placeholder = {
                                DiceColoredText(
                                    title = "Enter Name",
                                    style = MaterialTheme.typography.bodyLarge
                                )
                            },
                        )
                        ElevatedButton(
                            onClick = {
                                viewModel.insertNameToDatabase()
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = colorResource(id = R.color.dice_color)
                            )
                        ) {
                            Text(text = "Add")
                        }
                    }
                }
            }

        }
    }
}











