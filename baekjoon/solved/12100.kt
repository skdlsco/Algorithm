package `12100`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun copyBoard(N: Int, initBoard: Array<Array<Int>>): Array<Array<Int>> {
    return Array<Array<Int>>(N) { y ->
        Array(N) { x ->
            initBoard[y][x]
        }
    }
}

class GameBoard(val N: Int, val board: Array<Array<Int>>) {
    fun getMax(): Int = board.maxOf { it.maxOf { it } }

    fun moveLeft() {
        for (y in 0 until N) {
            // make newLine
            val newLine = ArrayList<Int>()
            var isMerged = false
            for (x in 0 until N) {
                if (board[y][x] != 0) {
                    if (!isMerged && newLine.isNotEmpty() && newLine.last() == board[y][x]) {
                        newLine[newLine.lastIndex] *= 2
                        isMerged = true
                    } else {
                        newLine.add(board[y][x])
                        isMerged = false
                    }
                }
            }
            // put newLine
            for (x in 0 until newLine.size)
                board[y][x] = newLine[x]
            for (x in newLine.size until N)
                board[y][x] = 0
        }
    }

    fun moveRight() {
        for (y in 0 until N) {
            // make newLine
            val newLine = ArrayList<Int>()
            var isMerged = false
            for (x in N - 1 downTo 0) {
                if (board[y][x] != 0) {
                    if (!isMerged && newLine.isNotEmpty() && newLine.last() == board[y][x]) {
                        newLine[newLine.lastIndex] *= 2
                        isMerged = true
                    } else {
                        newLine.add(board[y][x])
                        isMerged = false
                    }
                }
            }
            // put newLine
            for (x in N - 1 downTo 0) {
                if (newLine.isNotEmpty()) {
                    board[y][x] = newLine.first()
                    newLine.removeFirst()
                } else
                    board[y][x] = 0
            }
        }
    }

    fun moveTop() {
        for (x in 0 until N) {
            // make newLine
            val newLine = ArrayList<Int>()
            var isMerged = false
            for (y in N - 1 downTo 0) {
                if (board[y][x] != 0) {
                    if (!isMerged && newLine.isNotEmpty() && newLine.last() == board[y][x]) {
                        newLine[newLine.lastIndex] *= 2
                        isMerged = true
                    } else {
                        newLine.add(board[y][x])
                        isMerged = false
                    }
                }
            }
            // put newLine
            for (y in N - 1 downTo 0) {
                if (newLine.isNotEmpty()) {
                    board[y][x] = newLine.first()
                    newLine.removeFirst()
                } else
                    board[y][x] = 0
            }
        }
    }

    fun moveBottom() {
        for (x in 0 until N) {
            // make newLine
            val newLine = ArrayList<Int>()
            var isMerged = false
            for (y in 0 until N) {
                if (board[y][x] != 0) {
                    if (!isMerged && newLine.isNotEmpty() && newLine.last() == board[y][x]) {
                        newLine[newLine.lastIndex] *= 2
                        isMerged = true
                    } else {
                        newLine.add(board[y][x])
                        isMerged = false
                    }
                }
            }
            // put newLine
            for (y in 0 until newLine.size)
                board[y][x] = newLine[y]
            for (y in newLine.size until N)
                board[y][x] = 0
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val initBoard = Array<Array<Int>>(N) { Array(N) { 0 } }
    for (y in 0 until N) {
        initBoard[y] = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    }
    var result = 2
    // 4진수
    for (bit in 0 until (1 shl 10)) {
        // i를 두개씩 끊어서 0,1,2,3 중 행동 한가지 경우
        // '최대' 5번 이동이므로 이동할 때마다 max 값 체크
        val board = GameBoard(N, copyBoard(N, initBoard))
        for (i in 0 until 5) {
            // move left, right, top, bottom
            val direction = 3 and (bit shr i * 2)
            when (direction) {
                0 -> board.moveTop()
                1 -> board.moveBottom()
                2 -> board.moveLeft()
                3 -> board.moveRight()
            }
            result = max(board.getMax(), result)
        }
    }
    writer.write("${result}\n")
    writer.flush()
}