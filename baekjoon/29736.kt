package `29736`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (A, B) = reader.readLine().split(" ").map { it.toInt() }
    val (K, X) = reader.readLine().split(" ").map { it.toInt() }
    val cnt = (A..B).count {
        it in (K - X)..(K + X)
    }
    if (cnt == 0)
        writer.write("IMPOSSIBLE")
    else
        writer.write("${cnt}\n")
    writer.flush()
}
    