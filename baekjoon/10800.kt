import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

fun main() {
    val N = reader.readLine().toInt()
    val ans = Array<Long>(N) { 0 }
    // [size] = (color, idx)
    val balls = Array<ArrayList<Pair<Int, Int>>>(2001) { ArrayList() }
    repeat(N) {
        val (color, size) = reader.readLine().split(" ").map { it.toInt() }
        balls[size].add(Pair(color, it))
    }
    val colors = Array<Long>(N + 1) { 0L }
    var total = 0L
    for (size in 1..2000) {
        for ((color, idx) in balls[size]) {
            ans[idx] = total - colors[color]
        }
        for ((color, _) in balls[size]) {
            total += size
            colors[color] += size.toLong()
        }
    }
    writer.write(ans.joinToString("\n"))
    writer.flush()
}
