package `D`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Int>(1001) { 0 }
    reader.readLine().split(" ").map { it.toInt() }.forEach {
        arr[it]++
    }
    val ans = arr.sumOf { minOf(it, 2) }
    writer.write("${ans}\n")
    writer.flush()
}
    