package com.xs.rolladice.ui.historypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.xs.rolladice.ui.homepage.MyTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryRollScreen(
    viewModel: HistoryPageViewModel = hiltViewModel()
){
    val historyList = viewModel.historyRolls.collectAsState(initial = emptyList())
    Scaffold(
        topBar = { MyTopBar("History of Previous Rolls", Icons.Default.DateRange) }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize().background(color = Color.White)) {
            LazyColumn(modifier = Modifier){
                items(items = historyList.value){ historyRoll ->
                    HistoryItems( onDelete = { viewModel.onDeleteHistory(historyRoll) }, historyRoll )
                }
            }
        }
    }
}

