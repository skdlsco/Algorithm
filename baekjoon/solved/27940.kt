package `27940`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    var isBroken = false
    var sum = 0
    for (i in 1..M) {
        val (t, r) = reader.readLine().split(" ").map { it.toInt() }
        sum += r
        if (sum > K) {
            isBroken = true
            writer.write("${i} ${1}")
            break
        }
    }
    if (!isBroken)
        writer.write("-1")
    writer.flush()
}