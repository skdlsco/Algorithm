package `29726`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }
    val first = Array<Int>(M + 1) { 0 }
    first[0] = arr[0]
    for (i in 1..M) {
        first[i] = min(first[i - 1], arr[i])
    }
    val last = Array<Int>(M + 1) { 0 }
    last[0] = arr[N - 1]
    for (i in 1..M) {
        last[i] = max(last[i - 1], arr[N - i - 1])
    }
    var ans = last[M] - first[0]
    for (i in 0..M) {
        ans = max(ans, last[M - i] - first[i])
    }
    writer.write("${ans}\n")
    writer.flush()
}
    