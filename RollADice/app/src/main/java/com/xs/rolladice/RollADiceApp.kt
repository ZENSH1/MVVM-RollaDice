package com.xs.rolladice

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/** Dice Roll App
 *
 * Functionality:
 * 1. Select any Name or Add New Name and Select it
 *
 * 2. You can Press a Button to Roll the dice, dice will roll a random and show on screen,
 *  -> This is also saved in HistoryRoll (with name and date{no idea how to do that yet})
 *
 * 3. View the history of all the previous rolls and also option to delete individual history.
 *
 *  Seems Simple Enough...
 */
@HiltAndroidApp
class RollADiceApp: Application()