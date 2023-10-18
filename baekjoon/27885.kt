package `27885`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (c, h) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Int>(c + h) { 0 }
    repeat(c + h) {
        val input = reader.readLine().split(":").map { it.toInt() }
        val sec = input[2] + input[1] * 60 + input[0] * 3600
        arr[it] = sec
    }
    var len = 0
    var start = 0
    var end = 0
    arr.sort()
    arr.forEach {
        if (it + 40 <= end)
            return@forEach
        if (it > end) {
            len += end - start
            start = it
        }
        end = it + 40
    }
    len += end - start
    writer.write("${86400 - len}\n")
    writer.flush()
}