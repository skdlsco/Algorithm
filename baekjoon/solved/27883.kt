package `27883`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    writer.write("${m * 2 + 1}\n")
    writer.write("U 1 -1\n")
    repeat(m) {
        val target = reader.readLine().toInt()
        writer.write("U ${1 + target} ${it + 1}\n")
        writer.write("P\n")
    }
    writer.flush()
}