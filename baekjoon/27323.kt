import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val A = reader.readLine().toInt()
    val B = reader.readLine().toInt()
    writer.write("${A * B}\n")
    writer.flush()
}