package com.xs.rolladice.ui.homepage

import android.view.animation.BounceInterpolator
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInOutBack
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.EaseOutBounce
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.rotationMatrix
import androidx.hilt.navigation.compose.hiltViewModel
import com.xs.rolladice.R
import com.xs.rolladice.ui.theme.MVVMRollADiceAppTheme
import com.xs.rolladice.utils.DrawableHandler
import com.xs.rolladice.utils.ROUTES
import com.xs.rolladice.utils.UiEvent
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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
                HomeHeading(onClicked = { viewModel.sendUiEvent(UiEvent.Navigate(ROUTES.View_History + "?name=${viewModel.name}")) })
                DiceHolder(
                    DrawableHandler.DrawablesDiceData[viewModel.diceRoll],
                    viewModel.name,
                    viewModel.diceRolled,
                    viewModel.diceRotation
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
    val infiniteTransition = rememberInfiniteTransition(label = "Text")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(800, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "Text"
    )
    Column(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                onClicked()
            }
            .defaultMinSize(),
    ) {

        DiceColoredText(
            modifier = Modifier.fillMaxWidth().graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
            title = stringResource(R.string.roll_a_dice_app),
            style = MaterialTheme.typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        DiceColoredText(
            title = stringResource(R.string.by_person_name),
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                ,
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Right,
            fontFamily = FontFamily.Cursive
        )

    }
}


@Composable
fun DiceHolder(
    diceNumPair: DrawableHandler.DiceNumPair = DrawableHandler.DrawablesDiceData[1],
    name: String,
    diceRolled: Boolean,
    textVisible: Boolean = false
) {

    var rotateState by remember { mutableStateOf(false) }
    val scaleAnimation = remember { Animatable(1f) }
    val yAnimation = remember { Animatable(0f) }
    val xAnimation = remember { Animatable(0f) }

    LaunchedEffect(key1 = diceRolled, block = {
        if (diceRolled) {
            rotateState = true
            //Scale Down and Animate to Bottom
            scaleAnimation.animateTo(0.5f, animationSpec = tween(200, easing = EaseInOutBounce))
            yAnimation.animateTo(400f, animationSpec = tween(900, easing = EaseOutBounce))
            //Animate : Move Right and Back to Center
            launch {
                xAnimation.animateTo(500f, animationSpec = tween(500))
            }
            launch {
                yAnimation.animateTo(0f, tween(500))
                //Move to top and Center
                launch {
                    xAnimation.animateTo(0f, animationSpec = tween(500))
                }
                launch {
                    yAnimation.animateTo(-400f, tween(500))
                    //Move to Left and Center
                    launch {
                        xAnimation.animateTo(-500f, animationSpec = tween(500))
                    }
                    launch {
                        yAnimation.animateTo(0f, tween(500))
                        //back to bottom
                        launch {
                            xAnimation.animateTo(0f, animationSpec = tween(500))
                        }
                        launch {
                            yAnimation.animateTo(400f, tween(500))
                        }
                    }
                }
            }
        } else {
            launch {
                yAnimation.animateTo(0f)
            }
            launch {
                scaleAnimation.animateTo(
                    1f, animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessVeryLow
                    )
                )
            }
            rotateState = false
        }
    })

    Column(
        modifier = Modifier,
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        val infiniteTransition = rememberInfiniteTransition(label = "Dice Rotation")
        val angle by infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 360f,
            animationSpec = infiniteRepeatable(
                animation = tween(500, easing = LinearEasing),
                repeatMode = RepeatMode.Restart
            ),
            label = "Dice Rotation"
        )
        Image(
            painter = painterResource(id = diceNumPair.drawable),
            contentDescription = "Dice",
            modifier = Modifier
                .padding(15.dp)
                .graphicsLayer {
                    scaleX = scaleAnimation.value
                    scaleY = scaleAnimation.value
                    translationY = yAnimation.value
                    translationX = xAnimation.value
                    rotationZ = (if (rotateState) angle else 0f)
                }
        )
        AnimatedVisibility(visible = textVisible) {
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
                disabledContainerColor = Color.DarkGray,
            ),
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

@Preview
@Composable
fun PreviewHome() {
    MVVMRollADiceAppTheme {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeHeading {}
            DiceHolder(name = "Zain", diceRolled = true)
            ButtonHolder(changeName = { /*TODO*/ }, onRollClick = { /*TODO*/ }, diceRolled = true)
        }
    }
}

@Preview(
    device = "spec:width=1080px,height=2340px,dpi=440,isRound=true",
    backgroundColor = 0xFFE91E63
)
@Composable
fun previewDiceRoll() {
    MVVMRollADiceAppTheme {
        DiceHolder(name = "Zain", diceRolled = true)
    }
}


