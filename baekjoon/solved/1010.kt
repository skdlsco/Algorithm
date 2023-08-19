package `1010`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.log2

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val t = reader.readLine().toInt()

    repeat(t) {
        val (N, M) = reader.readLine().split(" ").map { it.toInt() }
        var ans = 1L

        for (i in 0 until N) {
            ans *= M - i
            ans /= i + 1
        }
        writer.write("${ans}\n")
    }
    writer.flush()
}
