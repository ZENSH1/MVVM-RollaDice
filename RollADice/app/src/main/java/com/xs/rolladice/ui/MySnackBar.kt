package com.xs.rolladice.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xs.rolladice.ui.homepage.DiceColoredText
import com.xs.rolladice.ui.theme.MVVMRollADiceAppTheme

@Composable
fun MySnackBar(
    snackbarHostState:SnackbarHostState,
    onUndoDelete: ()-> Unit
){
    SnackbarHost(hostState = snackbarHostState) {
        Card(
            modifier = Modifier.padding(horizontal = 5.dp),
            elevation = CardDefaults.cardElevation(10.dp),
            colors = CardDefaults.cardColors(Color.White)
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                DiceColoredText(title = it.visuals.message, textAlign = TextAlign.Center, style = MaterialTheme.typography.labelLarge)
                TextButton(onClick = onUndoDelete) {
                    Text(text = "" + it.visuals.actionLabel)
                }
            }
        }
    }
}

@Composable
fun SnackBarUi(it:SnackbarData?, onUndoDelete: () -> Unit){
    Card(
        modifier = Modifier.padding(horizontal = 5.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DiceColoredText(title = it!!.visuals.message, textAlign = TextAlign.Center, style = MaterialTheme.typography.labelLarge)
            TextButton(onClick = onUndoDelete) {
                Text(text = "" + it.visuals.actionLabel)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSnackBar(){
    MVVMRollADiceAppTheme {
        SnackBarUi(it = null) {

        }
    }
}
