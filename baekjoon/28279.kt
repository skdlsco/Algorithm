import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = Array<Int>(3000002) { 0 }
    var start = 2000000
    var end = 2000001
    repeat(N) {
        val input = StringTokenizer(reader.readLine())
        val command = input.nextToken()
        when (command) {
            "1" -> {
                val X = input.nextToken().toInt()
                arr[start--] = X
            }
            "2" -> {
                val X = input.nextToken().toInt()
                arr[end++] = X
            }
            "3" -> {
                if (end - start == 1)
                    writer.write("-1\n")
                else {
                    writer.write("${arr[start + 1]}\n")
                    start++
                }
            }
            "4" -> {
                if (end - start == 1)
                    writer.write("-1\n")
                else {
                    writer.write("${arr[end - 1]}\n")
                    end--
                }
            }
            "5" -> {
                writer.write("${end - start - 1}\n")
            }
            "6" -> {
                if (end - start == 1)
                    writer.write("1\n")
                else
                    writer.write("0\n")
            }
            "7" -> {
                if (end - start == 1)
                    writer.write("-1\n")
                else
                    writer.write("${arr[start + 1]}\n")
            }
            "8" -> {
                if (end - start == 1)
                    writer.write("-1\n")
                else
                    writer.write("${arr[end - 1]}\n")
            }
        }
    }
    writer.flush()
}