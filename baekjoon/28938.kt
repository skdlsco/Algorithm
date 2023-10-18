package `28938`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val ans = reader.readLine().split(" ").map { it.toInt() }.sum()
    if (ans < 0)
        writer.write("Left")
    else if (ans > 0)
        writer.write("Right")
    else
        writer.write("Stay")
    writer.flush()
}
    