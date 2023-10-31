import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Int>(2000001) { 0 }
    var start = 0
    var end = 0
    repeat(N) {
        val input = StringTokenizer(reader.readLine())
        val command = input.nextToken()
        when (command) {
            "push" -> {
                val x = input.nextToken().toInt()
                arr[end++] = x
            }
            "front" -> {
                if (end - start == 0)
                    writer.write("-1\n")
                else
                    writer.write("${arr[start]}\n")
            }
            "back" -> {
                if (end - start == 0)
                    writer.write("-1\n")
                else
                    writer.write("${arr[end - 1]}\n")
            }
            "pop" -> {
                if (end - start == 0)
                    writer.write("-1\n")
                else {
                    writer.write("${arr[start]}\n")
                    start++
                }
            }
            "size" -> {
                writer.write("${end - start}\n")
            }
            "empty" -> {
                if (end - start == 0)
                    writer.write("1\n")
                else
                    writer.write("0\n")
            }
        }
    }
    writer.flush()
}