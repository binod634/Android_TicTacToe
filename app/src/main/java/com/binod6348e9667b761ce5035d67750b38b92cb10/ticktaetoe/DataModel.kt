package com.binod6348e9667b761ce5035d67750b38b92cb10.ticktaetoe

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


enum class Player {
    First, Second, NoOne
}


data class Board3x3 (
    val arr: Array<Array<Player>> = arrayOf(
        arrayOf(Player.NoOne,Player.NoOne,Player.NoOne),
        arrayOf(Player.NoOne,Player.NoOne,Player.NoOne),
        arrayOf(Player.NoOne,Player.NoOne,Player.NoOne)
    ),
    var gameOver: Boolean = false
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Board3x3

        if (!arr.contentDeepEquals(other.arr)) return false

        return true
    }

    override fun hashCode(): Int {
        return arr.contentDeepHashCode()
    }
}

object GameModel {
    private var board = Board3x3()
    private val priv_uiState = MutableStateFlow(board)

    val uiState = priv_uiState.asStateFlow()

    fun resets() {
        board = Board3x3()
        priv_uiState.value = board
    }

    private var currentPlayer = Player.First

    fun plotData( position: Int) {
        if (checkWon() == Player.NoOne) {
            board.arr[position / 3][position % 3] = currentPlayer  
            priv_uiState.value = board
//            boardData[position / 3][position % 3] = currentPlayer

            // Changing player turn.
            if (currentPlayer == Player.First) {
                currentPlayer = Player.Second
            } else if (currentPlayer == Player.Second) {
                currentPlayer = Player.First
            }
        } else {
            board.gameOver = true
            priv_uiState.value = board
        }
    }

    fun checkWon(): Player {
        repeat(3) {
            if ((board.arr[it][0] == board.arr[it][1]) && (board.arr[it][1] == board.arr[it][2]) && (board.arr[it][0] != Player.NoOne)) {
                return board.arr[it][0]
            } else if ((board.arr[0][it] == board.arr[1][it]) && (board.arr[1][it] == board.arr[2][it]) && (board.arr[0][it] != Player.NoOne)) {
                return board.arr[0][it]
            }
        }
        if ((board.arr[0][0] == board.arr[1][1]) && (board.arr[1][1] == board.arr[2][2]) && (board.arr[1][1] != Player.NoOne)) {
            return board.arr[1][1]
        } else if ((board.arr[2][0] == board.arr[1][1]) && (board.arr[1][1] == board.arr[0][2]) && (board.arr[1][1] != Player.NoOne)) {
            return board.arr[1][1]
        }
        return Player.NoOne
    }
}