package `27973`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val Q = reader.readLine().toInt()
    var diff = 1L
    var cur = 1L
    repeat(Q) {
        val tokenizer = StringTokenizer(reader.readLine())
        val command = tokenizer.nextToken().toInt()
        when (command) {
            0 -> {
                cur += tokenizer.nextToken().toLong()
            }

            1 -> {
                val i = tokenizer.nextToken().toLong()
                diff *= i
                cur *= i
            }

            2 -> {
                cur += tokenizer.nextToken().toLong() * diff
            }

            3 -> {
                writer.write("${cur}\n")
            }
        }
    }
    writer.flush()
}