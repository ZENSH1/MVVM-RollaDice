package com.xs.rolladice.utils

sealed class UiEvent{
    object PopBackStack: UiEvent()
    data class Navigate(val route:String): UiEvent()
    data class ShowSnackBar(val msg: String, val action: String? = null): UiEvent()
    object ShowDialogBox: UiEvent()
}
