package `30033`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val A = reader.readLine().split(" ").map { it.toInt() }
    val B = reader.readLine().split(" ").map { it.toInt() }
    val ans = (0 until N).count { A[it] <= B[it] }
    writer.write("${ans}\n")
    writer.flush()
}
    