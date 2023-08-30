package com.xs.rolladice.ui.homepage

sealed class HomePageEvent {
    object OnRollClick: HomePageEvent()
    object OnChangeNameClick: HomePageEvent()
    object OnHistoryClick: HomePageEvent()
}