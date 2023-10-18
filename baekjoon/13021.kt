package `13021`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Int>(N) { -1 }
    repeat(M) {
        val (L, R) = reader.readLine().split(" ").map { it.toInt() - 1 }
        for (i in L..R) {
            arr[i] = it
        }
    }
    val set = arr.filter { it != -1 }.toSet()
    val ans = 1L shl set.size
    writer.write("${ans}\n")
    writer.flush()
}
    