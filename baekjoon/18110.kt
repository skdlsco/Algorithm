package `18110`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Math.round

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = reader.readLine().toInt()
    val arr = (0 until n).map { reader.readLine().toInt() }.sorted()
    val cut = round(n * 0.15).toInt()

    var sum = 0
    var total = 0
    for (i in cut until n - cut) {
        sum += arr[i]
        total++
    }
    if (n == 0)
        writer.write("0\n")
    else
        writer.write("${round(sum / total.toDouble()).toInt()}\n")
    writer.flush()
}
    