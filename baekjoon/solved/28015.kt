package `28015`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val canvas = Array<Array<Int>>(N) { Array(M) { 0 } }
    for (y in 0 until N) {
        val line = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until M) {
            canvas[y][x] = line[x]
        }
    }
    var cnt = 0
    for (y in 0 until N) {
        var prev = 0
        var oneCnt = 0
        var twoCnt = 0
        for (x in 0 until M) {
            val cur = canvas[y][x]
            if (prev != cur && prev == 0) {
                oneCnt = 0
                twoCnt = 0
                if (cur == 1)
                    oneCnt++
                else
                    twoCnt++
                cnt++
            }
            if (prev != 0 && cur != 0 && prev != cur) {
                if (cur == 1)
                    oneCnt++
                else
                    twoCnt++
            }
            if (prev != cur && cur == 0 || cur != 0 && x == M - 1)
                cnt += min(oneCnt, twoCnt)
            prev = cur
        }
    }
    writer.write("${cnt}\n")
    writer.flush()
}