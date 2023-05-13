package `2720`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val coin = arrayOf(25, 10, 5, 1)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        var C = reader.readLine().toInt()
        coin.forEach {
            var cnt = 0
            if (C > 0) {
                cnt = C / it
                C %= it
            }
            writer.write("${cnt} ")
        }
        writer.newLine()
        writer.flush()
    }
}