package `14890`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, L) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Int>>(N) { reader.readLine().split(" ").map { it.toInt() }.toTypedArray() }
    var cnt = 0
    for (i in 0 until N) {
        // x
        var prev = map[i][0]
        var isXValid = true
        var slopeCnt = 1
        for (j in 1 until N) {
            if (abs(prev - map[i][j]) > 1)
                isXValid = false
            if (prev != map[i][j]) {
                if (prev > map[i][j]) {
                    if (slopeCnt < 0)
                        isXValid = false
                    slopeCnt = -L
                } else {
                    if (slopeCnt < L)
                        isXValid = false
                    slopeCnt = 0
                }
            }
            slopeCnt++
            prev = map[i][j]
        }
        if (slopeCnt < 0)
            isXValid = false
        // y
        prev = map[0][i]
        var isYValid = true
        slopeCnt = 1
        for (j in 1 until N) {
            if (abs(prev - map[j][i]) > 1)
                isYValid = false
            if (prev != map[j][i]) {
                if (prev > map[j][i]) {
                    if (slopeCnt < 0)
                        isYValid = false
                    slopeCnt = -L
                } else {
                    if (slopeCnt < L)
                        isYValid = false
                    slopeCnt = 0
                }
            }
            slopeCnt++
            prev = map[j][i]
        }
        if (slopeCnt < 0)
            isYValid = false
        if (isYValid)
            cnt++
        if (isXValid)
            cnt++
    }
    writer.write("${cnt}\n")
    writer.flush()
}