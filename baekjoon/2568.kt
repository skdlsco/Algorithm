package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeSet

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = ArrayList<Pair<Int, Int>>()
    repeat(N) {
        val (A, B) = reader.readLine().split(" ").map { it.toInt() }
        arr.add(Pair(A, B))
    }
    arr.sortBy { it.first }
    val dp = Array<Pair<Int, Int>>(500001) { Pair(Int.MAX_VALUE, -1) }
    val path = Array<Int>(500001) { -1 }
    for ((a, b) in arr) {
        var left = 0
        var right = 500000
        while (left < right) {
            val mid = (left + right) / 2
            if (dp[mid].first <= b)
                left = mid + 1
            else
                right = mid
        }
        dp[left] = Pair(b, a)
        path[a] = if (left == 0) a else dp[left - 1].second
    }
    val select = TreeSet<Int>()
    var cur = dp.findLast { it.first != Int.MAX_VALUE }!!.second
    while (path[cur] != cur) {
        select.add(cur)
        cur = path[cur]
    }
    select.add(cur)
    writer.write("${N - select.size}\n")
    writer.write(arr.filter { !select.contains(it.first) }
            .sortedBy { it.first }
            .joinToString("\n") { it.first.toString() })
    writer.flush()
}
    