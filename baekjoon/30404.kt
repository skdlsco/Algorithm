package `C`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }.reversed()
    var cnt = 0
    var left = -1
    var prev = arr[0]
    for (x in arr) {
        left -= prev - x
        if (left < 0) {
            cnt++
            left = K
        }
        prev = x
    }
    writer.write("${cnt}\n")
    writer.flush()
}
    