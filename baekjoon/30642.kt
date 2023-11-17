package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeSet

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val S = reader.readLine()
    val K = reader.readLine().toInt()
    if (S == "annyong") {
        if (K % 2 == 1)
            writer.write("${K}\n")
        else {
            writer.write("${maxOf(K - 1, 1)}\n")
        }
    } else {
        if (K % 2 == 0)
            writer.write("${K}\n")
        else {
            writer.write("${maxOf(K - 1, 2)}\n")
        }
    }
    writer.flush()
}
    