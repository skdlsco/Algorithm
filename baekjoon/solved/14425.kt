package `14425`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val set = TreeSet<String>()
    repeat(N) {
        set.add(reader.readLine())
    }
    val ans = (0 until M).count {
        set.contains(reader.readLine())
    }
    writer.write("${ans}\n")
    writer.flush()
}