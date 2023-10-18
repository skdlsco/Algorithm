package `11005`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var (N, B) = reader.readLine().split(" ").map { it.toInt() }
    val result = StringBuilder()
    while (N > 0) {
        val number = N % B
        if (number < 10)
            result.append(('0'.code + number).toChar())
        else
            result.append(('A'.code + number - 10).toChar())
        N /= B
    }
    writer.write(result.reverse().toString())
    writer.flush()
}