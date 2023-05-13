package `2903`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Int>(16) { 0 }
    arr[0] = 4
    for (i in 1 until 16) {
        arr[i] = arr[i - 1] * 4 - ((2 shl i) + 3)
    }
    writer.write("${arr[N]}\n")
    writer.flush()
}