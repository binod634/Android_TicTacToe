package com.binod6348e9667b761ce5035d67750b38b92cb10.ticktaetoe

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.binod6348e9667b761ce5035d67750b38b92cb10.ticktaetoe.ui.theme.customBackground

@Composable
fun AppStart() {
    val gameover by GameModel.uiState.collectAsState()
    Card(elevation = CardDefaults.cardElevation(8.dp)) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(customBackground)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.9f)
            ) {

                if (gameover.gameOver) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Player ${GameModel.checkWon().name} Won !!!")
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(3)
                ) {
                    items(9) {

                        // make clickable
                        BoxModel(
                            count = it
                        ) { GameModel.plotData(it) }
                    }
                }
                Box(modifier = Modifier.fillMaxWidth()) {
                    Button(
                        onClick = { GameModel.resets() },
//                        colors = ButtonDefaults.buttonColors(Color.Gray),
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(text = "Reset Game")
                    }
                }
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)) {
                    Text(
                        color = Color.Black,
                        text = "Sorry. Complete Feature is not implemented yet.",
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Composable
fun BoxModel(
    count: Int,
    clicked: () -> Unit = {}
) {
    val some by GameModel.uiState.collectAsState()
    Box(
            modifier = Modifier
                .height(100.dp)
                .background(Color.Black)
                .padding(1.dp)
                .border(width = 1.dp, color = Color.White, shape = RectangleShape),
            contentAlignment = Alignment.Center
        ) {
            // for Recomposing Screen
            val recompose = remember {
                mutableStateOf(false)
            }


            Box(
                modifier = Modifier.padding(5.dp)
            ) {
//                when (GameModel.boardData[count / 3][count % 3]!!
                when (some.arr[count/3][count%3]) {
                    Player.First -> {
                        Icon(painterResource(id = R.drawable.circleweb), contentDescription = null, tint = Color.Red)
                    }

                    Player.Second -> {
                        Icon(painterResource(id = R.drawable.cross), contentDescription = null, modifier = Modifier.alpha(1f), tint = Color.Green)
                    }

                    else -> {
                        Icon(
                            painterResource(id = R.drawable.cross),
                            contentDescription = null,
                            modifier = Modifier
                                .alpha(0f)
                                .clickable { clicked();recompose.value = true }
                        )
                    }
                }
            }
            if (recompose.value) {
                recompose.value = false
            }
        }
}

@Preview(showBackground = true)
@Composable
fun Some() {
    AppStart()
}
