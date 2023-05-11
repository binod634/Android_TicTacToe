package com.binod6348e9667b761ce5035d67750b38b92cb10.ticktaetoe

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow


enum class Player {
    First, Second, NoOne
}

enum class WON {
    First, Second, Draw
}



object GameModel: ViewModel() {
    var boardData: Array<Array<Player?>> = Array(3) { arrayOfNulls(3) }
    val uiState = MutableStateFlow(boardData)
    var currentPlayer = Player.First



    fun clearboard() {
        repeat(3) { it1 ->
            repeat(3) { it2 ->
                boardData[it1][it2] = Player.NoOne
            }
        }
    }

    fun plotData( position: Int) {
        if (checkWon() == Player.NoOne) {
            boardData[position / 3][position % 3] = currentPlayer

            // Changing player turn.
            if (currentPlayer == Player.First) {
                currentPlayer = Player.Second
            } else if (currentPlayer == Player.Second) {
                currentPlayer = Player.First
            }
        }
    }

    fun checkWon(): Player {
        repeat(3) {
            if ((boardData[it][0] == boardData[it][1]) && (boardData[it][1] == boardData[it][2]) && (boardData[it][0] != Player.NoOne)) {
                return boardData[it][0]!!
            } else if ((boardData[0][it] == boardData[1][it]) && (boardData[1][it] == boardData[2][it]) && (boardData[0][it] != Player.NoOne)) {
                return boardData[0][it]!!
            }
        }
        if ((boardData[0][0] == boardData[1][1]) && (boardData[1][1] == boardData[2][2]) && (boardData[1][1] != Player.NoOne)) {
            return boardData[1][1]!!
        } else if ((boardData[2][0] == boardData[1][1]) && (boardData[1][1] == boardData[0][2]) && (boardData[1][1] != Player.NoOne)) {
            return boardData[1][1]!!
        }
        return Player.NoOne
    }
}