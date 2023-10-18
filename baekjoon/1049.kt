package `1049`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var (N, M) = reader.readLine().split(" ").map { it.toInt() }
    var pack = Int.MAX_VALUE
    var single = Int.MAX_VALUE
    repeat(M) {
        val (p, s) = reader.readLine().split(" ").map { it.toInt() }
        pack = minOf(pack, p)
        single = minOf(single, s)
    }
    pack = minOf(pack, single * 6)
    val ans = (N / 6) * pack + minOf((N % 6) * single, pack)
    writer.write("${ans}\n")
    writer.flush()
}
    