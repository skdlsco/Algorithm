package `27880`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var sum = 0
    repeat(4) {
        val (command, count) = reader.readLine().split(" ")
        if (command == "Es") {
            sum += count.toInt() * 21
        } else {
            sum += count.toInt() * 17
        }
    }
    writer.write("${sum}\n")
    writer.flush()
}