package `A`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    val Q = reader.readLine().toInt()
    repeat(Q) {
        val input = reader.readLine().split(" ").map { it.toInt() }
        if (input[0] == 1) {
            val ans = ((input[1] - 1)..(input[2] - 1)).count {
                arr[it] == input[3]
            }
            writer.write("${ans}\n")
        } else {
            ((input[1] - 1)..(input[2] - 1)).forEach {
                arr[it] = 0
            }
        }
    }
    writer.flush()
}
    