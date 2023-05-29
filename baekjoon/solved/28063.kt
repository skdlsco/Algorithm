package `28063`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val (x, y) = reader.readLine().split(" ").map { it.toInt() }
    val result = if (N == 1)
        0
    else {
        if ((x == 1 && (y == 1 || y == N)) || (x == N && (y == 1 || y == N)))
            2
        else if (x == 1 || x == N || y == 1 || y == N)
            3
        else
            4
    }
    writer.write("$result")
    writer.flush()
}