package com.xs.rolladice.utils

import androidx.annotation.DrawableRes
import com.xs.rolladice.R

object DrawableHandler {
    val DrawablesDiceData = listOf(
       R.drawable.num1 to 1,
        R.drawable.num2 to 2,
        R.drawable.num3 to 3,
        R.drawable.num4 to 4,
        R.drawable.num5 to 5,
        R.drawable.num6 to 6,

    ).map { DiceNumPair(it.first, it.second) }


     data class DiceNumPair(@DrawableRes val drawable: Int,
                            val num: Int)

}