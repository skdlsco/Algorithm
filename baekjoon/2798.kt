package `2798`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val cards = reader.readLine().split(" ").map { it.toInt() }

    var result = 0
    for (i in 0 until N - 2) {
        for (j in i + 1 until N - 1) {
            for (k in j + 1 until N) {
                val sum = cards[i] + cards[j] + cards[k]
                if (sum <= M)
                    result = max(result, sum)
            }
        }
    }
    writer.write("$result")
    writer.flush()
}