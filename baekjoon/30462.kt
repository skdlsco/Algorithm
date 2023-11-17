package `K`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.Queue

fun valid(N: Int, arr: List<Int>): Boolean {
    var prev = 0
    for ((i, v) in arr.withIndex()) {
        if (v < prev)
            return false
        if (v > i + 2)
            return false
        prev = v
    }
    return prev == N + 1
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val bArr = reader.readLine().split(" ").map { it.toInt() }
    if (valid(N, bArr)) {
        val queue = ArrayDeque<Int>()
        var prev = 1
        val queueArr = Array<ArrayList<Int>>(N) { ArrayList() }
        for ((i, v) in bArr.withIndex()) {
            queue.add(i + 1)
            if (v > prev) {
                repeat(v - prev) {
                    queueArr[i].add(queue.removeFirst())
                }
            }
            prev = v
        }
        val ans = ArrayList<Int>()
        for (i in N - 1 downTo 0) {
            queue.addAll(queueArr[i].reversed())
            ans.add(queue.removeLast())
        }
        writer.write("Yes\n")
        writer.write(ans.reversed().joinToString(" "))
    } else {
        writer.write("No")
    }
    writer.flush()
}
    