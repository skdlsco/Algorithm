package `10828`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = IntArray(10000) { 0 }
    var size = 0
    repeat(N) {
        val input = reader.readLine().split(" ")
        when (input[0]) {
            "push" -> {
                arr[size] = input[1].toInt()
                size++
            }

            "top" -> {
                if (size == 0)
                    writer.write("-1\n")
                else
                    writer.write("${arr[size - 1]}\n")
            }

            "size" -> {
                writer.write("${size}\n")
            }

            "empty" -> {
                if (size == 0)
                    writer.write("1\n")
                else
                    writer.write("0\n")
            }

            "pop" -> {
                if (size == 0)
                    writer.write("-1\n")
                else {
                    writer.write("${arr[size - 1]}\n")
                    size--
                }
            }
        }
    }
    writer.flush()
}
    