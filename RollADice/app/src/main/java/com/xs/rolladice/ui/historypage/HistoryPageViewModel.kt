package com.xs.rolladice.ui.historypage

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xs.rolladice.data.local.model.HistoryRoll
import com.xs.rolladice.repository.HistoryRollRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

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