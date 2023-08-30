package com.xs.rolladice.ui.splashScreen

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.xs.rolladice.R
import com.xs.rolladice.ui.homepage.DiceColoredText
import com.xs.rolladice.ui.theme.MVVMRollADiceAppTheme
import com.xs.rolladice.utils.ROUTES
import com.xs.rolladice.utils.UiEvent
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SplashScreen(
    viewModel: SplashScreenViewModel = hiltViewModel(),
    onNavigate: (UiEvent.Navigate) -> Unit
){
    LaunchedEffect(key1 = true, block = {
        viewModel.uiEvent.collectLatest {
            when(it){
                is UiEvent.Navigate -> {
                    onNavigate(it)
                }
                UiEvent.PopBackStack -> TODO()
                UiEvent.ShowDialogBox -> TODO()
                is UiEvent.ShowSnackBar -> TODO()
            }
        }
    })
Animatedmylogo(viewModel.logoMovement)
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Preview
@Composable
fun Animatedmylogo(atEnd: Boolean = false) {

        val image = AnimatedImageVector.animatedVectorResource(R.drawable.lion_to_my_name_perfected)

        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
            Arrangement.Center,
            Alignment.CenterHorizontally) {
            Image(
                painter = rememberAnimatedVectorPainter(image, atEnd),
                contentDescription = "Timer",
                modifier = Modifier.background(color = Color.Transparent),
                contentScale = ContentScale.FillWidth
            )
            DiceColoredText(title = "Superb Zen",
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.displayLarge,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Cursive)
        }

}