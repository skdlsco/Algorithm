package `27966`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toLong()
    if (N == 2L)
        writer.write("${1}\n")
    else
        writer.write("${N - 1L + (N - 1L) * (N - 2L)}\n")
    for (i in 2 ..N) {
        writer.write("1 ${i}\n")
    }
    writer.flush()
}