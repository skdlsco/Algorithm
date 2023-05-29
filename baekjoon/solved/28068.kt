package `28068`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val gain = ArrayList<Pair<Long, Long>>()
    val loss = ArrayList<Pair<Long, Long>>()
    repeat(N) {
        val (a, b) = reader.readLine().split(" ").map { it.toLong() }
        if (a <= b)
            gain.add(Pair(a, b))
        else
            loss.add(Pair(a, b))
    }
    gain.sortBy { it.first }
    loss.sortByDescending { it.second }
    var happiness = 0L
    var isPossible = true
    for ((a, b) in gain) {
        if (a > happiness)
            isPossible = false
        happiness += b - a
    }
    for ((a, b) in loss) {
        if (a > happiness)
            isPossible = false
        happiness += b - a
    }
    writer.write(if (isPossible) "1" else "0")
    writer.flush()
}