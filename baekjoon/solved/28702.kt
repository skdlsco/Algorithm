package `28702`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val a = reader.readLine()
    val b = reader.readLine()
    val c = reader.readLine()
    val d = if (a[0].isDigit()) {
        a.toInt() + 3
    } else if (b[0].isDigit()) {
        b.toInt() + 2
    } else {
        c.toInt() + 1
    }
    val result = if (d % 3 == 0 && d % 5 == 0) "FizzBuzz"
    else if (d % 3 == 0)
        "Fizz"
    else if (d % 5 == 0)
        "Buzz"
    else d.toString()
    writer.write("${result}\n")
    writer.flush()
}