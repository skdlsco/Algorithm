package `10101`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val a = reader.readLine().toInt()
    val b = reader.readLine().toInt()
    val c = reader.readLine().toInt()
    val sum = a + b + c
    if (a == 60 && b == 60 && c == 60)
        writer.write("Equilateral")
    else if (sum == 180) {
        if (a == b || b == c || a == c)
            writer.write("Isosceles")
        else
            writer.write("Scalene")
    } else
        writer.write("Error")
    writer.flush()
}