package `10989`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Int>(10001) { 0 }
    repeat(N) {
        val n = reader.readLine().toInt()
        arr[n]++
    }
    for (i in 1..10000) {
        repeat(arr[i]) {
            writer.write("$i\n")
        }
    }
    writer.flush()
    writer.close()
}