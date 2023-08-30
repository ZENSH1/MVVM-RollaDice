package com.xs.rolladice.ui.namepage

import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.animation.graphics.res.rememberAnimatedVectorPainter
import androidx.compose.animation.graphics.vector.AnimatedImageVector
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xs.rolladice.R
import com.xs.rolladice.data.Name
import com.xs.rolladice.ui.homepage.DiceColoredText

@Composable
fun NameItem(
    modifier: Modifier = Modifier,
    name: Name,
    onDeleteName: () -> Unit,
    onShowPopUp: () -> Unit
) {
    Card(
        modifier = modifier.padding(5.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            Arrangement.SpaceBetween
        ) {
            DiceColoredText(
                title = name.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 20.dp)
                    .fillMaxHeight(),
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Serif
            )
            Column {
                IconButton(onClick = onDeleteName) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Name",
                        tint = colorResource(id = R.color.dice_color)
                    )
                }

                IconButton(onClick = onShowPopUp) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Name",
                        tint = colorResource(id = R.color.dice_color)
                    )
                }
            }
        }
    }

}




