import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arrA = reader.readLine().split(" ").map { it == "0" }
    val arrB = reader.readLine().split(" ").map { it.toInt() }
    val queue = LinkedList<Int>()
    queue.addAll((0 until N).filter { arrA[it] }.map {
        arrB[it]
    }.reversed())
    val M = reader.readLine().toInt()
    val ans = reader.readLine().split(" ").map { it.toInt() }.map {
        queue.add(it)
        queue.pop()
    }
    writer.write(ans.joinToString(" "))
    writer.flush()
}