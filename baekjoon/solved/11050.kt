package `11050`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    var result = 1
    for (i in 0 until K) {
        result *= N - i
    }
    for (i in 1..K) {
        result /= i
    }
    writer.write("${result}")
    writer.flush()
}