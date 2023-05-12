package `10798`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val arr = Array<String>(5) { "" }
    repeat(5) {
        arr[it] = reader.readLine()
    }
    val maxX = arr.maxOf { it.length }
    for (x in 0 until maxX) {
        for (y in 0 until 5) {
            if (x < arr[y].length )
                writer.write("${arr[y][x]}")
        }
    }
    writer.flush()
}