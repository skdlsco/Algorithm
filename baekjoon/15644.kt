package `15644`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Point(val y: Int, val x: Int)

val dy = arrayOf(0, 0, 1, -1)
val dx = arrayOf(1, -1, 0, 0)

class GameBoard(
    val N: Int, val M: Int, val board: Array<Array<Char>>,
    var redPos: Point, var bluePos: Point
) {

    fun canMove(cur: Point, other: Point, next: Point): Boolean {
        if (next.y !in 0 until N || next.x !in 0 until M)
            return false
        if (other == next)
            return false
        if (board[next.y][next.x] == '#')
            return false
        return true
    }

    fun isInHole(p: Point): Boolean {
        return board[p.y][p.x] == 'O'
    }

    fun move(direction: Int): Int {
        var isRedInHole = false
        var isBlueInHole = false
        var isRedMoved = true
        var isBlueMoved = true
        while (isRedMoved || isBlueMoved) {
            isRedMoved = false
            isBlueMoved = false
            val nextRedPos = Point(redPos.y + dy[direction], redPos.x + dx[direction])
            if (canMove(redPos, bluePos, nextRedPos)) {
                redPos = nextRedPos
                if (isInHole(redPos)) {
                    isRedInHole = true
                    redPos = Point(-1, -1)
                }
                isRedMoved = true
            }
            val nextBluePos = Point(bluePos.y + dy[direction], bluePos.x + dx[direction])
            if (canMove(bluePos, redPos, nextBluePos)) {
                bluePos = nextBluePos
                if (isInHole(bluePos)) {
                    isBlueInHole = true
                    bluePos = Point(-1, -1)
                }
                isBlueMoved = true
            }
        }
        if (isBlueInHole)
            return -1
        if (isRedInHole)
            return 1
        return 0
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val board = Array<Array<Char>>(N) { Array(M) { '.' } }
    var redStart = Point(0, 0)
    var blueStart = Point(0, 0)
    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until M) {
            if (line[x] == 'R')
                redStart = Point(y, x)
            else if (line[x] == 'B')
                blueStart = Point(y, x)
            else
                board[y][x] = line[x]
        }
    }

    val mask = 3
    var result = Int.MAX_VALUE
    var path = ""
    // 4진수
    for (bit in 0 until (1 shl 20)) {
        val gameBoard = GameBoard(N, M, board, redStart, blueStart)
        val sb = StringBuilder()
        for (i in 0 until 10) {
            val direction = mask and (bit shr i * 2)

            when (direction) {
                0 -> sb.append("R")
                1 -> sb.append("L")
                2 -> sb.append("D")
                3 -> sb.append("U")
            }
            val gameCondition = gameBoard.move(direction)
            if (gameCondition == 1) {
                if (result > i + 1) {
                    result = i + 1
                    path = sb.toString()
                }
                break
            }
            if (gameCondition == -1) {
                break
            }
        }
    }
    if (result == Int.MAX_VALUE)
        writer.write("-1\n")
    else
        writer.write("${result}\n${path}\n")
    writer.flush()
}