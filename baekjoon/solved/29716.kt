package `29716`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (J, N) = reader.readLine().split(" ").map { it.toInt() }
    var cnt = 0
    repeat(N) {
        val line = reader.readLine()
        val sum = line.sumOf {
            if (it.isDigit() || it.isLowerCase())
                2L
            else if (it.isUpperCase())
                4L
            else
                1L
        }
        if (sum <= J)
            cnt++
    }
    writer.write("${cnt}")
    writer.flush()
}
    