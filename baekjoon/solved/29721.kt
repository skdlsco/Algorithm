package `29721`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeSet

val dx = arrayOf(0, 0, 2, -2)
val dy = arrayOf(2, -2, 0, 0)
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toLong() }
    val set = TreeSet<Long>()
    val positions = TreeSet<Long>()
    val arr = Array<Pair<Long, Long>>(K.toInt()) {
        val (X, Y) = reader.readLine().split(" ").map { it.toLong() - 1 }
        positions.add(X + Y * N)
        Pair(X, Y)
    }
    for ((X, Y) in arr) {
        for (i in 0 until 4) {
            val nx = X + dx[i]
            val ny = Y + dy[i]
            if (nx in 0 until N && ny in 0 until N) {
                val data = nx + ny * N
                if (!positions.contains(data))
                    set.add(nx + ny * N)
            }
        }
    }
    writer.write("${set.size}\n")
    writer.flush()
}
    