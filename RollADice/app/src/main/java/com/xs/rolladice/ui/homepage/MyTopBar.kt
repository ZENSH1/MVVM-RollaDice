package com.xs.rolladice.ui.homepage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.xs.rolladice.R

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MyTopBar(
    title: String = "Home Page",
    icon: ImageVector = Icons.Default.Home
){
    Surface(modifier = Modifier, shadowElevation = 10.dp, tonalElevation = 5.dp) {
        TopAppBar(
            title = {
                Row(
                    modifier = Modifier
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,


                    ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        modifier = Modifier.padding(5.dp),
                        tint = colorResource(id = R.color.dice_color)
                    )

                    DiceColoredText(
                        title = title,
                        style = MaterialTheme.typography.titleLarge,
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold
                    )

                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = Color.White),
        )
    }

}
