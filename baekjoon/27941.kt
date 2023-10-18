package `27941`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val visited = Array<Boolean>(2048) { false }
    val input = Array<Array<Int>>(2047) { Array(11) { 0 } }
    val lArr = Array<Int>(11) { 0 }
    val rArr = Array<Int>(11) { 0 }
    repeat(2047) {
        input[it] = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    }
    for (i in 0 until 11) {
        lArr[i] = (0 until 2047).minOf { input[it][i] }
        rArr[i] = (0 until 2047).maxOf { input[it][i] }
    }
    repeat(2047) {
        var idx = 0
        input[it].forEachIndexed { index, i ->
            if (i == rArr[index])
                idx += 1 shl index
        }
        visited[idx] = true
    }
    val result = visited.indexOf(false)
    for (i in 0 until 11) {
        if (result and (1 shl i) == 0)
            writer.write("${lArr[i]} ")
        else
            writer.write("${rArr[i]} ")
    }
    writer.flush()
}