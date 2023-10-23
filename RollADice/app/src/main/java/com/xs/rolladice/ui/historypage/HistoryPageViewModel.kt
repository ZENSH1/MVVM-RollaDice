package com.xs.rolladice.ui.historypage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import com.xs.rolladice.repository.HistoryRollRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xs.rolladice.data.HistoryRoll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@HiltViewModel
class HistoryPageViewModel @Inject constructor(
    private val historyRollRepository: HistoryRollRepository,
    savedStateHandle: SavedStateHandle
): ViewModel(){
    var historyRolls: Flow<List<HistoryRoll>>
    var name:String
    fun onDeleteHistory(value: HistoryRoll){
        viewModelScope.launch {
            historyRollRepository.deleteHistoryRoll(value)
        }
    }

    init {
        name = savedStateHandle.get<String>("name").toString()
        historyRolls = historyRollRepository.getHistoryRolls(name)
    }

}