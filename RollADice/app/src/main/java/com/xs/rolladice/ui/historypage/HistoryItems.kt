package com.xs.rolladice.ui.historypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.xs.rolladice.data.local.model.HistoryRoll
import com.xs.rolladice.ui.homepage.DiceColoredText
import com.xs.rolladice.utils.DrawableHandler

@Composable
fun HistoryItems(
    onDelete: () -> Unit,
    historyRoll: HistoryRoll
){
    Card(modifier = Modifier.padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(10.dp)) {
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth().heightIn(min = 20.dp, max = 70.dp),
            Arrangement.SpaceBetween) {
            Column {
                DiceColoredText(title = "Rolled By: ${historyRoll.name}", style = MaterialTheme.typography.labelLarge)
                DiceColoredText(title = "Roll: ${historyRoll.roll}", style = MaterialTheme.typography.labelLarge)
                DiceColoredText(title = "Date: ${historyRoll.date}", style = MaterialTheme.typography.labelLarge)
            }
            Image(painter = painterResource(id = DrawableHandler.DrawablesDiceData[historyRoll.roll-1].drawable),
                contentDescription = "roll",
                contentScale = ContentScale.Inside)
            IconButton(onClick = onDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.Red)
            }

        }
    }
}