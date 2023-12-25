import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.FileReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.PriorityQueue
import java.util.Stack
import java.util.StringTokenizer
import kotlin.math.*

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val ans = (1..N).count {
        reader.readLine().count { it == 'O' } > M / 2
    }
    writer.write("${ans}\n")
    writer.flush()
}
