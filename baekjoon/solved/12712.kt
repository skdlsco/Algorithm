package `12712`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    repeat(N) {
        val (P, K, L) = reader.readLine().split(" ").map { it.toInt() }
        val arr = reader.readLine().split(" ").map { it.toLong() }.sortedDescending()
        var sum = 0L
        for (i in arr.indices) {
            sum += arr[i] * ((i / K) + 1)
        }
        writer.write("Case #${it + 1}: ${sum}\n")
    }
    writer.flush()
}