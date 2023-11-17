package `H`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, Q) = reader.readLine().split(" ").map { it.toInt() }
    val prefixSum = Array<Array<Long>>(N + 1) { Array(M + 1) { 0 } }
    val arr = Array<Array<Long>>(N + 1) { Array(M + 1) { 0 } }
    for (y in 1..N) {
        val row = reader.readLine().split(" ").map { it.toLong() }
        for (x in 1..M) {
            arr[y][x] = row[x - 1]
        }
    }
    for (x in 1..M) {
        var verticalSum = 0L
        for (y in 1..N) {
            verticalSum += arr[y][x]
            prefixSum[y][x] = prefixSum[y - 1][x - 1] + verticalSum
        }
    }
    repeat(Q) {
        val (W, P) = reader.readLine().split(" ").map { it.toInt() }
        writer.write("${prefixSum[W][P]}\n")
    }
    writer.flush()
}
    