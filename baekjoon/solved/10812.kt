package `10812`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Int>(N) { it + 1 }
    repeat(M) {
        val (s, e, m) = reader.readLine().split(" ").map { it.toInt() - 1 }
        val a = arr.slice(m..e)
        val b = arr.slice(s until m)
        var idx = s
        for (n in a) {
            arr[idx] = n
            idx++
        }
        for (n in b) {
            arr[idx] = n
            idx++
        }
    }
    writer.write(arr.joinToString(" "))
    writer.flush()
}