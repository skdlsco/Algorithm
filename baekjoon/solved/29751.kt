package `29751`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (W, H) = reader.readLine().split(" ").map { it.toInt() }
    writer.write(String.format("%.1f", W * H * 0.5))
    writer.flush()
}
    