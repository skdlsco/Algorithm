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
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toLong() }.sorted()
    writer.write("${(arr.max() - arr.min()) * 2}\n")
    writer.flush()
}