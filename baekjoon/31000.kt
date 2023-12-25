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
    val N = reader.readLine().toLong()
    var ans = 0L
    for (a in -N..N) {
        for (b in -N..N) {
            if (a == 0L) {
                ans += 2 * N + 1
            } else {
                val c = 1 - a - b
                if (c in -N..N)
                    ans++
            }
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}
