package com.xs.rolladice.ui.namepage

import com.xs.rolladice.data.Name

/** Useless For Now
 */
sealed class NamePageEvent {
    //Done
    object OnAddName: NamePageEvent()
    //Done
    object OnViewName: NamePageEvent()
    //Done
    object OnUndoDelete: NamePageEvent()
    //Todo: Navigation to other screen
    data class OnNameClick(val name: String): NamePageEvent()
}