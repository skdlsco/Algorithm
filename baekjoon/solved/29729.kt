package `29729`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (S, N, M) = reader.readLine().split(" ").map { it.toInt() }
    var capacity = S
    var size = 0
    repeat(N + M) {
        val command = reader.readLine().toInt()
        if (command == 1)
            size++
        else
            size--
        if (size > capacity)
            capacity *= 2
    }
    writer.write("${capacity}\n")
    writer.flush()
}
    