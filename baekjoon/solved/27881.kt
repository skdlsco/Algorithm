package `27881`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer
import kotlin.math.min

var N = 0
val prefixSum = Array<Long>(400002) { 0 }

fun calcDistance(start: Int, end: Int): Long {
    return if (start < end) {
        min(
            prefixSum[end] - prefixSum[start],
            prefixSum[start] + prefixSum[N * 4 + 1] - prefixSum[end]
        )
    } else {
        min(
            prefixSum[start] - prefixSum[end],
            prefixSum[N * 4 + 1] - prefixSum[start] + prefixSum[end]
        )
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    N = reader.readLine().toInt()
    val stringTokenizer = StringTokenizer(reader.readLine())
    for (i in 2..N * 4 + 1) {
        prefixSum[i] = prefixSum[i - 1] + stringTokenizer.nextToken().toLong()
    }
    val stationDistance = Array<Long>(4) { 0L }
    repeat(4) {
        stationDistance[it] = reader.readLine().split(" ").minOf { it.toLong() }
    }
    val Q = reader.readLine().toInt()
    repeat(Q) {
        val start = reader.readLine().toInt()
        // 시계 방향 vs 반시계 방향
        var end = N
        var result = Long.MAX_VALUE
        repeat(4) {
            result = min(result, calcDistance(start, end) + stationDistance[it])
            end += N
        }
        writer.write("${result}\n")
    }
    writer.flush()
}