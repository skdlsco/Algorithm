package `5073`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    while (true) {
        val (a, b, c) = reader.readLine().split(" ").map { it.toInt() }.sorted()
        if (a == 0 && b == 0 && c == 0)
            break
        if (c >= b + a)
            writer.write("Invalid")
        else if (a == b && b == c)
            writer.write("Equilateral")
        else if (a == b || b == c || a == c)
            writer.write("Isosceles")
        else
            writer.write("Scalene")
        writer.newLine()
    }
    writer.flush()
}