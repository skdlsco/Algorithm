package `27967`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val line = reader.readLine()
    var openCount = line.count { it == '(' }
    var closeCount = line.count { it == ')' }
    assert(N % 2 == 0)
    for (i in 0 until N) {
        if (line[i] == 'G') {
            if (openCount < N / 2) {
                writer.write("(")
                openCount++
            } else
                writer.write(")")
        } else {
            writer.write("${line[i]}")
        }
    }
    writer.flush()
}