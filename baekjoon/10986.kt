package `10986`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }
    val prefixSum = Array<Int>(N + 1) { 0 }
    val remainCnt = Array<Long>(M) { 0 }
    for (i in 1..N) {
        prefixSum[i] = prefixSum[i - 1] + arr[i - 1]
        prefixSum[i] %= M
        remainCnt[prefixSum[i]]++
    }
    var ans = remainCnt[0]
    for (i in 0 until M) {
        ans += remainCnt[i] * (remainCnt[i] - 1) / 2
    }
    writer.write("${ans}\n")
    writer.flush()
}