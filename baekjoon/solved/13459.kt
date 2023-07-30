package `13459`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

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
    // 4진수
    for (bit in 0 until (1 shl 20)) {
        val gameBoard = GameBoard(N, M, board, redStart, blueStart)
        for (i in 0 until 10) {
            val direction = mask and (bit shr i * 2)
            val gameCondition = gameBoard.move(direction)
            if (gameCondition == 1) {
                result = min(result, i + 1)
                break
            }
            if (gameCondition == -1) {
                break
            }
        }
    }
    writer.write("${if (result == Int.MAX_VALUE) 0 else 1}\n")
    writer.flush()
}