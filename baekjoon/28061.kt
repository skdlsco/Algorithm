package `28061`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val lemonTreeArr = reader.readLine().split(" ").map { it.toInt() }
    var result = 0
    for (i in lemonTreeArr.indices) {
        result = max(result, lemonTreeArr[i] - (N - i))
    }
    writer.write("$result")
    writer.flush()
}