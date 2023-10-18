package `14391`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val paper = Array<Array<Int>>(N) { Array(M) { 0 } }
    for (i in 0 until N) {
        val line = reader.readLine().map { it.digitToInt() }
        for (j in 0 until M) {
            paper[i][j] = line[j]
        }
    }
    var result = 0
    for (bit in 0 until (1 shl N * M)) {
        var sum = 0
        // 위에서 부터 탐색
        for (i in 0 until N * M) {
            val y = i / M
            val x = i % M
            // 0: 가로 1: 세로
            if (bit and (1 shl i) > 0) {
                if (y == 0 || bit and (1 shl (i - M)) == 0) {
                    var num = 0
                    num = paper[y][x]
                    for (j in y + 1 until N) {
                        if (bit and (1 shl (x + j * M))== 0)
                            break
                        num *= 10
                        num += paper[j][x]
                    }
                    sum += num
                }
            } else {
                if (x == 0 || bit and (1 shl (i - 1)) > 0) {
                    var num = 0
                    num = paper[y][x]
                    for (j in x + 1 until M) {
                        if (bit and (1 shl (y * M + j)) > 0)
                            break
                        num *= 10
                        num += paper[y][j]
                    }
                    sum += num
                }
            }
        }
        result = max(sum, result)
    }
    writer.write("${result}\n")
    writer.flush()
}
