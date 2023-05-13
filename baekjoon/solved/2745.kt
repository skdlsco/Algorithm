package `2745`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val stringTokenizer = StringTokenizer(reader.readLine())
    val N = stringTokenizer.nextToken()
    val B = stringTokenizer.nextToken().toInt()
    var result = 0
    var base = 1
    for (c in N.reversed()) {
        if (c in "0123456789") {
            result += (c.code - '0'.code) * base
        } else {
            result += (10 + c.code - 'A'.code) * base
        }
        base *= B
    }
    writer.write("${result}\n")
    writer.flush()
}