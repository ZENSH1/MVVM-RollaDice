package com.xs.rolladice.ui.homepage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xs.rolladice.data.HistoryRoll
import com.xs.rolladice.repository.HistoryRollRepository
import com.xs.rolladice.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class HomePageViewModel @Inject constructor(
    private val historyRepository: HistoryRollRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var diceRolled by mutableStateOf(false)
        private set
    var buttonPressable by mutableStateOf(true)
        private set

    var diceRoll by mutableStateOf(0)
    var name by mutableStateOf("G")

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        name = savedStateHandle.get<String?>("name").toString()
    }

    fun onEvent(event: HomePageEvent) {
        when (event) {
            HomePageEvent.OnChangeNameClick -> {

            }

            HomePageEvent.OnHistoryClick -> {


            }

            HomePageEvent.OnRollClick -> {
                viewModelScope.launch {
                    buttonPressable = false

                    diceRoll = Random.nextInt(6)
                    historyRepository.insertHistoryRoll(
                        historyRoll = HistoryRoll(
                            name = name,
                            roll = diceRoll+1,
                            date = LocalDateTime.now().toString()
                        )
                    )
                    delay(100)
                    diceRolled = true
                    delay(1000)
                    buttonPressable = true
                    delay(4000)
                    diceRolled = false



                }

            }
        }
    }

    fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}