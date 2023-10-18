package `28110`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }.sorted()
    var ans = 0
    var maxDiff = 0
    val impossible = (1 until N).all { arr[it] == (arr[it - 1] + 1) }
    for (i in 0 until N - 1) {
        val x = (arr[i] + arr[i + 1]) / 2
        val diff = min(x - arr[i], arr[i + 1] - x)
        if (maxDiff < diff) {
            maxDiff = diff
            ans = x
        }
    }
    if (impossible)
        writer.write("-1\n")
    else
        writer.write("${ans}\n")
    writer.flush()
}
    