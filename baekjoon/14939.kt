package `14939`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

val dx = arrayOf(0, 0, 0, 1, -1)
val dy = arrayOf(0, 1, -1, 0, 0)

fun clone(origin: Array<Array<Boolean>>): Array<Array<Boolean>> {
    return Array<Array<Boolean>>(10) { y -> Array(10) { x -> origin[y][x] } }
}

fun click(board: Array<Array<Boolean>>, y: Int, x: Int) {
    for (i in dx.indices) {
        val ny = y + dy[i]
        val nx = x + dx[i]
        if (ny in 0 until 10 && nx in 0 until 10)
            board[ny][nx] = !board[ny][nx]
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val origin = Array<Array<Boolean>>(10) { Array(10) { false } }

    for (y in 0 until 10) {
        val row = reader.readLine()
        for (x in 0 until 10) {
            origin[y][x] = row[x] == 'O'
        }
    }
    var ans = Int.MAX_VALUE
    for (bit in 0 until 1024) {
        val board = clone(origin)
        var cnt = 0
        for (i in 0 until 10) {
            val b = 1 shl i
            if (bit and b > 0) {
                click(board, 0, i)
                cnt++
            }
        }
        for (y in 1 until 10) {
            for (x in 0 until 10) {
                if (board[y - 1][x]) {
                    click(board, y, x)
                    cnt++
                }
            }
        }
        var success = true
        for (y in 0 until 10) {
            for (x in 0 until 10) {
                if (board[y][x])
                    success = false
            }
        }
        if (success)
            ans = min(ans, cnt)
    }
    if (ans == Int.MAX_VALUE)
        writer.write("-1")
    else writer.write("${ans}")
    writer.flush()
}