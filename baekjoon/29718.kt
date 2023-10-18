package `29718`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Int>(M + 1) { 0 }
    repeat(N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until M) {
            arr[x + 1] += row[x]
        }
    }
    val A = reader.readLine().toInt()
    var sum = 0
    for (i in 1..A) {
        sum += arr[i]
    }
    var ans = sum
    for (i in A + 1..M) {
        sum += -arr[i - A] + arr[i]
        ans = max(ans, sum)
    }
    writer.write("${ans}\n")
    writer.flush()
}
    