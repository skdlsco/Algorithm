package `2750`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Int>(N) { 0 }
    repeat(N) {
        arr[it] = reader.readLine().toInt()
    }
    reader.close()
    arr.sort()
    arr.forEach {
        writer.write(it.toString() + "\n")
    }
    writer.flush()
    writer.close()
}