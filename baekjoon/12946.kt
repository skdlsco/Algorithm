package `12946`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import kotlin.math.max

val dx = arrayOf(0, 1, -1, 1, -1, 0)
val dy = arrayOf(-1, -1, 0, 0, 1, 1)

fun getColor(arr: Array<Array<Int>>, N: Int, y: Int, x: Int): Int {
    for (color in 1..2) {
        var enable = true
        for (i in dx.indices) {
            val nextY = y + dy[i]
            val nextX = x + dx[i]
            if (nextY in 0 until N && nextX in 0 until N &&
                arr[nextY][nextX] == color
            )
                enable = false
        }
        if (enable)
            return color
    }
    return 3
}

fun paint(arr: Array<Array<Int>>, N: Int, y: Int, x: Int) {
    val queue = LinkedList<Pair<Int, Int>>()

    queue.add(Pair(y, x))
    arr[y][x] = getColor(arr, N, y, x)
    while (queue.isNotEmpty()) {
        val (curY, curX) = queue.pop()
        for (i in dx.indices) {
            val nextY = curY + dy[i]
            val nextX = curX + dx[i]
            if (nextY in 0 until N && nextX in 0 until N && arr[nextY][nextX] == 0) {
                queue.add(Pair(nextY, nextX))
                arr[nextY][nextX] = getColor(arr, N, nextY, nextX)
            }
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Array<Int>>(N) { Array(N) { -1 } }
    for (y in 0 until N) {
        val line = reader.readLine()
        for (x in 0 until N) {
            if (line[x] == 'X')
                arr[y][x] = 0
        }
    }
    for (y in 0 until N) {
        for (x in 0 until N) {
            if (arr[y][x] == 0)
                paint(arr, N, y, x)
        }
    }
    if (arr.all { it.all { it == -1 } })
        writer.write("0\n")
    else
        writer.write("${arr.maxOf { it.maxOf { it } }}\n")
    writer.flush()
}