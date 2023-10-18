package `2501`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var (N, K) = reader.readLine().split(" ").map { it.toInt() }
    for (i in 1..N) {
        if (N % i == 0) {
            K--
            if (K == 0) {
                writer.write("${i}\n")
                break
            }
        }
    }
    if (K > 0)
        writer.write("0\n")
    writer.flush()
}