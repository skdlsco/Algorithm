package `16931`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val paper = Array<Array<Int>>(N) { Array(M) { 0 } }
    for (y in 0 until N) {
        val line = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until M) {
            paper[y][x] = line[x]
        }
    }

    // 위, 아래
    var cnt = N * M * 2
    // 좌우
    for (y in 0 until N) {
        var prev = 0
        for (x in 0 until M) {
            if (paper[y][x] > prev)
                cnt += paper[y][x] - prev
            prev = paper[y][x]
        }
        prev = 0
        for (x in M - 1 downTo 0) {
            if (paper[y][x] > prev)
                cnt += paper[y][x] - prev
            prev = paper[y][x]
        }
    }
    for (x in 0 until M) {
        var prev = 0
        for (y in 0 until N) {
            if (paper[y][x] > prev)
                cnt += paper[y][x] - prev
            prev = paper[y][x]
        }
        prev = 0
        for (y in N - 1 downTo 0) {
            if (paper[y][x] > prev)
                cnt += paper[y][x] - prev
            prev = paper[y][x]
        }
    }
    writer.write("${cnt}\n")
    writer.flush()
}