import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Stack
import java.util.StringTokenizer
import kotlin.math.*


val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val N = reader.readLine().toInt()
    repeat(N) { cur ->
        repeat(N) {
            writer.write("${cur + 1} ")
        }
        writer.newLine()
    }
    writer.flush()
}