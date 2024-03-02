package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    while (true) {
        val s = reader.readLine()
        if (s == "#")
            break
        writer.write("${s.count { it in "aeiouAEIOU" }}\n")
    }
    writer.flush()
}