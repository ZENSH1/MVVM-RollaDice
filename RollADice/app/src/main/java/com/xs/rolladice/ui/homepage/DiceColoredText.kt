package com.xs.rolladice.ui.homepage

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.xs.rolladice.R


@Composable
fun DiceColoredText(
    modifier: Modifier = Modifier,
    title: String,
    style: TextStyle = MaterialTheme.typography.labelSmall,
    fontFamily: FontFamily = FontFamily.Monospace,
    fontWeight: FontWeight = FontWeight.Normal,
    textAlign: TextAlign = TextAlign.Justify
){
    Text(
        modifier = modifier,
        text = title,
        style = style,
        color =  colorResource(id = R.color.dice_color),
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        textAlign = textAlign

    )

}