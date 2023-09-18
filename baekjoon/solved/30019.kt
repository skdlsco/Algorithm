package `30019`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val room = Array<Int>(N + 1) { 0 }
    repeat(M) {
        val (r, s, e) = reader.readLine().split(" ").map { it.toInt() }
        if (room[r] > s)
            writer.write("NO\n")
        else {
            writer.write("YES\n")
            room[r] = e
        }
    }
    writer.flush()
}