import java.io.*
import kotlin.collections.HashSet

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val A = reader.readLine().toInt()
    val B = reader.readLine().toInt()
    writer.write("${A + B}")
    writer.flush()
}