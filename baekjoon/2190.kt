import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun count(arr: List<Pair<Long, Long>>, x1: Long, y1: Long, x2: Long, y2: Long): Int {
    return arr.count {
        it.first in x1..x2 && it.second in y1..y2
    }
}

fun check(arr: List<Pair<Long, Long>>, x: Long, y: Long, A: Long, B: Long): Int {
    var ans = 0
    ans = max(ans, count(arr, x, y, x + A, y + B))
    ans = max(ans, count(arr, x - A, y, x, y + B))
    ans = max(ans, count(arr, x, y - B, x + A, y))
    ans = max(ans, count(arr, x - A, y - B, x, y))
    return ans
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, A, B) = reader.readLine().split(" ").map { it.toLong() }
    val arr = ArrayList<Pair<Long, Long>>()
    repeat(N.toInt()) {
        val (x, y) = reader.readLine().split(" ").map { it.toLong() }
        arr.add(Pair(x, y))
    }
    var ans = 0
    for ((x, y) in arr) {
        for ((x2, y2) in arr) {
            if (!(abs(x - x2) in 0..A && abs(y - y2) in 0..B))
                continue
            ans = max(ans, check(arr, min(x, x2), min(y, y2), A, B))
            ans = max(ans, check(arr, max(x, x2), min(y, y2), A, B))
            ans = max(ans, check(arr, min(x, x2), max(y, y2), A, B))
            ans = max(ans, check(arr, max(x, x2), max(y, y2), A, B))
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}
