package `23353`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun getMaxFromLine(line: List<Int>): Int {
    val arr = Array<Int>(line.size) { 0 }
    val white = Array<Int>(line.size) { 0 }
    var ans = 0
    arr[0] = if (line[0] == 1) 1 else 0
    white[0] = if (line[0] == 0) 0 else 1
    for (i in 1 until line.size) {
        when (line[i]) {
            0 -> {
                arr[i] = 0
                white[i] = 0
            }

            1 -> {
                arr[i] = arr[i - 1] + 1
                white[i] = white[i - 1] + 1
            }

            2 -> {
                white[i] = arr[i - 1] + 1
            }
        }
        ans = max(ans, arr[i])
        ans = max(ans, white[i])
    }
    return ans
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val board = Array<Array<Int>>(N) { Array(N) { 0 } }
    for (y in 0 until N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until N) {
            board[y][x] = row[x]
        }
    }
    var ans = 0
    // 가로세로
    for (y in 0 until N) {
        val hor = ArrayList<Int>()
        val ver = ArrayList<Int>()
        for (x in 0 until N) {
            hor.add(board[y][x])
            ver.add(board[x][y])
        }
        ans = max(ans, getMaxFromLine(hor))
        ans = max(ans, getMaxFromLine(ver))
        ver.clear()
        hor.clear()
    }
    for (sy in 0 until N) {
        var x = 0
        var y = sy
        val line1 = ArrayList<Int>()
        val line2 = ArrayList<Int>()
        while (x in 0 until N && y in 0 until N) {
            line1.add(board[y][x])
            line2.add(board[x][y])
            x++
            y++
        }
        ans = max(ans, getMaxFromLine(line1))
        ans = max(ans, getMaxFromLine(line2))
        line1.clear()
        line2.clear()
    }

    for (sy in N - 1 downTo 0) {
        var y = sy
        var x = 0
        val line1 = ArrayList<Int>()
        val line2 = ArrayList<Int>()
        while (x in 0 until N && y in 0 until N) {
            line1.add(board[y][x])
            line2.add(board[N - 1 - x][N - 1 - y])
            x++
            y--
        }
        ans = max(ans, getMaxFromLine(line1))
        ans = max(ans, getMaxFromLine(line2))
        line1.clear()
        line2.clear()
    }
    writer.write("${ans}\n")
    writer.flush()
}
    