import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun dist(p1: Pair<Long, Long>, p2: Pair<Long, Long>): Long {
    val dx = p1.first - p2.first
    val dy = p1.second - p2.second
    return dx * dx + dy * dy
}

fun solve(arr: Array<Pair<Long, Long>>, s: Int, e: Int): Long {
    if (s == e)
        return Long.MAX_VALUE
    val mid = (s + e) / 2
    val mx = arr[mid].first
    var d = minOf(solve(arr, s, mid), solve(arr, mid + 1, e))
    val target = ArrayList<Pair<Long, Long>>()
    for (i in s..e) {
        val dx = arr[i].first - mx
        // d가 거리의 제곱이기 dx를 제곱해서 비교
        if (dx * dx < d)
            target.add(arr[i])
    }
    target.sortBy { it.second }
    for (i in 0 until target.size - 1) {
        for (j in i + 1 until target.size) {
            val dy = target[i].second - target[j].second
            if (dy * dy < d)
                d = minOf(d, dist(target[i], target[j]))
            else
                break
        }
    }
    return d
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val n = reader.readLine().toInt()
    val arr = Array<Pair<Long, Long>>(n) { Pair(0, 0) }
    repeat(n) {
        val (x, y) = reader.readLine().split(" ").map { it.toLong() }
        arr[it] = Pair(x, y)
    }
    arr.sortBy { it.first }
    writer.write("${solve(arr, 0, n - 1)}\n")
    writer.flush()
}

