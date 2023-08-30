package com.xs.rolladice.ui.homepage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.xs.rolladice.R
import com.xs.rolladice.utils.DrawableHandler
import com.xs.rolladice.utils.ROUTES
import com.xs.rolladice.utils.UiEvent
import kotlinx.coroutines.flow.collectLatest

@ExperimentalMaterial3Api
@Composable
fun HomePageScreen(
    viewModel: HomePageViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit,
    onPopBackStack: (UiEvent.PopBackStack) -> Unit,

    ) {
    LaunchedEffect(key1 = true, block = {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> onNavigate(event)

                is UiEvent.PopBackStack -> {
                    onPopBackStack(UiEvent.PopBackStack)

                }

                is UiEvent.ShowSnackBar -> {

                }

                else -> {

                }
            }
        }
    })
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MyTopBar("Home Page", Icons.Default.Home) },
        content = {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(it),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                HomeHeading(onClicked = { viewModel.sendUiEvent(UiEvent.Navigate(ROUTES.View_History)) })
                DiceHolder(
                    DrawableHandler.DrawablesDiceData[viewModel.diceRoll],
                    viewModel.name,
                    viewModel.diceRolled
                )
                ButtonHolder(
                    changeName = { viewModel.sendUiEvent(UiEvent.PopBackStack) },
                    onRollClick = { viewModel.onEvent(HomePageEvent.OnRollClick) },
                    diceRolled = viewModel.buttonPressable
                )

            }
        },

        )
}


@Composable
fun HomeHeading(onClicked: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onClicked()
            }
            .defaultMinSize(),

        ) {
        DiceColoredText(
            modifier = Modifier.fillMaxWidth(),
            title = "Roll a Dice App",
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        DiceColoredText(
            title = "By Xi",
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Right,
            fontFamily = FontFamily.Cursive
        )
    }
}


@Preview
@Composable
fun DiceHolder(
    diceNumPair: DrawableHandler.DiceNumPair = DrawableHandler.DrawablesDiceData[1],
    name: String = "Guest",
    diceRolled: Boolean = true
) {
    Column(
        modifier = Modifier,
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = diceNumPair.drawable),
            contentDescription = "Dice",
            modifier = Modifier.padding(15.dp)
        )
        AnimatedVisibility(visible = diceRolled) {
            DiceColoredText(
                title = "$name Rolled ${diceNumPair.num}",
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

@Composable
fun ButtonHolder(changeName: () -> Unit, onRollClick: () -> Unit, diceRolled: Boolean) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(5.dp),
        Arrangement.SpaceEvenly
    ) {
        //Roll Btn
        ElevatedButton(
            onClick = onRollClick,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.dice_color),
                disabledContainerColor = Color.DarkGray,),
            shape = ButtonDefaults.elevatedShape,
            enabled = diceRolled
        ) {
            Text(text = "Roll Dice", color = Color.White)
        }

        //Name Btn
        ElevatedButton(
            onClick = changeName,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.dice_color),
                disabledContainerColor = Color.DarkGray
            ),
            shape = ButtonDefaults.elevatedShape,
        ) {
            Text(text = "Change Name", color = Color.White)
        }
    }
}




