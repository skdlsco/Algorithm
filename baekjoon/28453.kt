package `28453`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val M = reader.readLine().split(" ").map { it.toInt() }.map {
        if (it == 300)
            1
        else if (it >= 275)
            2
        else if (it >= 250)
            3
        else
            4
    }
    writer.write(M.joinToString(" "))
    writer.flush()
}