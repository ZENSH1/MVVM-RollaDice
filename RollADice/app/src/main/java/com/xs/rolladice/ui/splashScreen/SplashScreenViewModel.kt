package com.xs.rolladice.ui.splashScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xs.rolladice.utils.ROUTES
import com.xs.rolladice.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(): ViewModel() {

    var logoMovement by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            delay(1000)//Waiting for Screen to start
            logoMovement = true //Start Animation
            delay(2200)
            sendUiEvent(UiEvent.Navigate(ROUTES.Change_Name)) //Splash Screen Ends
        }
    }
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}