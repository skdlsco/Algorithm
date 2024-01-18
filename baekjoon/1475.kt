package `main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val str = reader.readLine()
    var cnt = 0
    val number = Array<Int>(9) { 0 }
    for (c in str) {
        var idx = c.code - '0'.code
        if (idx == 9)
            idx = 6
        if (number[idx] == 0) {
            for (i in 0..8)
                number[i]++
            number[6]++
            cnt++
        }
        number[idx]--
    }
    writer.write("${cnt}\n")
    writer.flush()
}
    