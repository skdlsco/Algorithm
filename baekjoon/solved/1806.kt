package `1806`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, S) = reader.readLine().split(" ").map { it.toInt() }
    var result = Int.MAX_VALUE
    var start = 0
    var end = 0
    var sum = 0
    val arr = reader.readLine().split(" ").map { it.toInt() }
    for (i in 0 until N) {
        sum += arr[i]
        end++
        while (sum >= S) {
            result = min(result, end - start)
            sum -= arr[start]
            start++
        }
    }
    writer.write("${if (result == Int.MAX_VALUE) 0 else result}\n")
    writer.flush()
}