package `15653`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
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

    var result = -1
    val queue = LinkedList<Pair<Int, Pair<Point, Point>>>()
    val visited = Array<Array<Array<Array<Boolean>>>>(10) { Array(10) { Array(10) { Array(10) { false } } } }
    val game = GameBoard(N, M, board, redStart, blueStart)
    queue.add(Pair(0, Pair(redStart, blueStart)))
    visited[redStart.x][redStart.y][blueStart.x][blueStart.y] = true
    while (queue.isNotEmpty() && result == -1) {
        val (c, p) = queue.pop()
        val (r, b) = p
        for (d in 0 until 4) {
            game.redPos = r
            game.bluePos = b
            val check = game.move(d)
            val nr = game.redPos
            val nb = game.bluePos
            if (check == 0) {
                if (!visited[nr.x][nr.y][nb.x][nb.y]) {
                    queue.add(Pair(c + 1, Pair(game.redPos, game.bluePos)))
                    visited[nr.x][nr.y][nb.x][nb.y] = true
                }
            } else if (check == 1) {
                result = c + 1
                break
            }
        }
    }
    writer.write("${result}\n")
    writer.flush()
}