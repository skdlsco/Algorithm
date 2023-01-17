import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*
import kotlin.math.abs

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val priorityQueue = PriorityQueue<Int>(kotlin.Comparator { o1, o2 ->
        if (abs(o1) - abs(o2) == 0)
            o1 - o2
        else
            abs(o1) - abs(o2)
    })
    repeat(N) {
        val num = reader.readLine().toInt()
        if (num == 0) {
            if (priorityQueue.isEmpty()) {
                writer.write("0")
            } else {
                writer.write("${priorityQueue.remove()}")
            }
            writer.newLine()
        } else {
            priorityQueue.add(num)
        }
    }
    writer.flush()
}