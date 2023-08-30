package com.xs.rolladice.ui.historypage

import com.xs.rolladice.repository.HistoryRollRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xs.rolladice.data.HistoryRoll
import kotlinx.coroutines.launch

@HiltViewModel
class HistoryPageViewModel @Inject constructor(
    private val historyRollRepository: HistoryRollRepository
): ViewModel(){
    var historyRolls = historyRollRepository.getHistoryRolls()

    fun onDeleteHistory(value: HistoryRoll){
        viewModelScope.launch {
            historyRollRepository.deleteHistoryRoll(value)
        }
    }

}