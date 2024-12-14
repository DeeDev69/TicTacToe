package deedev.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var statusText: TextView
    private val board = Array(3) { Array(3) { "" } }
    private var currentPlayer = "X"
    private var gameEnded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        statusText = findViewById(R.id.statusText)

        // Reset game when the app starts
        resetGame()
    }

    fun onCellClicked(view: View) {
        if (gameEnded) return

        val button = view as Button
        val tag = button.tag?.toString()  // Safely handle null tag
        if (tag == null) {
            return  // If tag is null, do nothing
        }

        val (row, col) = tag.split("_").map { it.toInt() }

        // Check if cell is empty
        if (board[row][col].isEmpty()) {
            board[row][col] = currentPlayer
            button.text = currentPlayer

            if (checkWinner()) {
                statusText.text = "$currentPlayer Wins!"
                gameEnded = true
            } else if (isBoardFull()) {
                statusText.text = "It's a Draw!"
                gameEnded = true
            } else {
                currentPlayer = if (currentPlayer == "X") "O" else "X"
                statusText.text = "$currentPlayer's Turn"
            }
        }
    }

    private fun isBoardFull(): Boolean {
        return board.all { row -> row.all { it.isNotEmpty() } }
    }

    private fun checkWinner(): Boolean {
        // Check rows, columns, and diagonals
        for (i in 0..2) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) return true
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) return true
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) return true
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) return true

        return false
    }

    private fun resetGame() {
        // Clear the board and reset the interface
        board.forEach { row -> row.fill("") }
        val gridLayout = findViewById<GridLayout>(R.id.gridLayout)

        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as Button
            button.text = ""
        }

        currentPlayer = "X"
        statusText.text = "$currentPlayer's Turn"
        gameEnded = false
    }
}
