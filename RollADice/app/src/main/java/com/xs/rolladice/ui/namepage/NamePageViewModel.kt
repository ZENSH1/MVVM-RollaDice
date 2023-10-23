package com.xs.rolladice.ui.namepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xs.rolladice.data.Name
import com.xs.rolladice.repository.NameRepository
import com.xs.rolladice.utils.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NamePageViewModel @Inject constructor(
    private val nameRepository: NameRepository
) : ViewModel() {




    val names = nameRepository.getNames()

    var showDialog by mutableStateOf(false)
        private set

    var showNameList by mutableStateOf(false)
        private set

    var nameField by mutableStateOf("")
        private set

    private var className by mutableStateOf<Name?>(null)

    private var recentDeletedName: Name? = null

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    //Db Functions
    fun onDeleteName(name: Name) {
        viewModelScope.launch {
            recentDeletedName = name
            nameRepository.deleteName(name)
            sendUiEvent(UiEvent.ShowSnackBar("${name.name} is deleted", "Undo"))
        }
    }

    fun insertNameToDatabase() {
        viewModelScope.launch {
            if (nameField.isBlank()) {
                sendUiEvent(UiEvent.ShowSnackBar("Please Input a Name"))
                return@launch
            }
            nameRepository.insertName(Name(nameField, className?.id))
            className = null
            nameField = ""
            onDialogDismiss()
        }
    }

    fun onUndoDelete() {
        viewModelScope.launch {
            if (recentDeletedName == null) {
                return@launch
            }
            nameRepository.insertName(recentDeletedName!!)
            recentDeletedName = null
        }
    }

    //viewModel Related
    fun onNameFieldChanged(newName: String) {
        nameField = newName
    }

    fun viewNamesList(state: Boolean) {
        showNameList = state
    }

    fun onDialogEditPop(name: Name) {
        onDialogPopup()
        className = name
        nameField = className!!.name
    }

    fun onDialogPopup() {
        showDialog = true
    }

    fun onDialogDismiss() {
        showDialog = false
    }

    //For ScreenStuff
    fun definesArrangement(): Arrangement.Vertical {
        if (showNameList) {
            return Arrangement.Top
        }
        return Arrangement.Center
    }


    //for sending UiEvents to the Screen where they will be handled and implemented
    fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}