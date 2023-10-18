package `20002`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val prefixSum = Array<Array<Int>>(N + 1) { Array(N + 1) { 0 } }
    for (y in 1..N) {
        val row = StringTokenizer(reader.readLine())
        for (x in 1..N) {
            prefixSum[y][x] = prefixSum[y][x - 1] + prefixSum[y - 1][x] +
                    row.nextToken().toInt() -
                    prefixSum[y - 1][x - 1]

        }
    }
    var ans = Int.MIN_VALUE
    for (k in 0 until N) {
        for (y in 1..N - k) {
            for (x in 1..N - k) {
                val sum = prefixSum[y + k][x + k] - prefixSum[y - 1][x + k] - prefixSum[y + k][x - 1] + prefixSum[y - 1][x - 1]
                ans = maxOf(ans, sum)
            }
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}
    