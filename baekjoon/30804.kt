import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.Arrays
import java.util.StringTokenizer
import java.util.TreeMap
import kotlin.math.*

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val input = reader.readLine().split(" ").map { it.toInt() }
    val selected = Array<Int>(10) { 0 }
    var kind = 0
    var size = 0
    var ans = 0
    val queue = ArrayDeque<Int>()
    for (cur in input) {
        size++
        if (selected[cur] == 0)
            kind++
        selected[cur]++
        queue.add(cur)
        if (kind > 2) {
            while (kind > 2 && queue.isNotEmpty()) {
                val remove = queue.removeFirst()
                selected[remove]--
                size--
                if (selected[remove] == 0)
                    kind--
            }
        }
        ans = maxOf(ans, size)
    }
    writer.write("${ans}")
    writer.flush()
}