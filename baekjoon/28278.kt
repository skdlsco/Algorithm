import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val X = reader.readLine().toInt()
    val arr = IntArray(1000000)
    var size = 0
    repeat(X) {
        val input = reader.readLine().split(" ").map { it.toInt() }
        when (input[0]) {
            1 -> {
                arr[size++] = input[1]
            }

            2 -> {
                if (size == 0)
                    writer.write("-1\n")
                else {
                    writer.write("${arr[size - 1]}\n")
                    size--
                }
            }

            3 -> {
                writer.write("${size}\n")
            }

            4 -> {
                writer.write("${if (size == 0) 1 else 0}\n")
            }

            5 -> {
                if (size == 0)
                    writer.write("-1\n")
                else
                    writer.write("${arr[size - 1]}\n")
            }
        }
    }
    writer.flush()
}