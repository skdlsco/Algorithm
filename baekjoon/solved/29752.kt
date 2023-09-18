package `29752`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    var longest = 0
    var cur = 0
    for (d in arr) {
        if (d > 0) {
            cur++
        } else
            cur = 0
        longest = max(longest, cur)
    }
    writer.write("${longest}\n")
    writer.flush()
}
    