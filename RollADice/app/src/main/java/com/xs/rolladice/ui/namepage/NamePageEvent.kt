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

    data class OnNameClick(val name: String): NamePageEvent()
}