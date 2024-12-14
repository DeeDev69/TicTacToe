package com.example.tictactoe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GameBoardView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var winningCells: List<Pair<Int, Int>> = emptyList()

    fun setWinningCells(cells: List<Pair<Int, Int>>) {
        winningCells = cells
        invalidate() // Re-draw the view when new cells are set
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val paint = Paint().apply {
            color = Color.RED
            strokeWidth = 10f
            isAntiAlias = true
        }

        if (winningCells.isNotEmpty()) {
            val cellSize = width / 3f // Assuming 3x3 grid
            val startX = winningCells[0].first * cellSize + cellSize / 2
            val startY = winningCells[0].second * cellSize + cellSize / 2
            val endX = winningCells[2].first * cellSize + cellSize / 2
            val endY = winningCells[2].second * cellSize + cellSize / 2

            canvas.drawLine(startX, startY, endX, endY, paint)
        }
    }
}
