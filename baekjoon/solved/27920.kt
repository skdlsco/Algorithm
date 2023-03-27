package `27920`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    writer.write("YES\n")
    var num = N - 2
    while (num > 0) {
        writer.write("$num ")
        num -= 2
    }
    writer.write("$N ")
    num = if (N % 2 == 0)
        1
    else 2
    while (num < N) {
        writer.write("$num ")
        num += 2
    }
    writer.newLine()
    num = 0
    while (num < N / 2) {
        writer.write("${N - num} ")
        writer.write("${num + 1} ")
        num++
    }
    if (N % 2 == 1)
        writer.write("${N / 2 + 1} ")
    writer.flush()
}