package `A3`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

data class Data(val A: Int, val B: Int, val id: Int) {

}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = ArrayList<Data>()
    repeat(N) {
        val input = reader.readLine().split(" ").map { it.toInt() }
        arr.add(Data(input[0], input[1], it))
    }
    val sortedA = arr.sortedBy { it.A }
    val sortedB = arr.sortedBy { it.B }
    val selected = Array<Boolean>(N + 1) { false }
    val deque = ArrayDeque<Int>()
    for (i in 0 until N) {
        if (selected[sortedA[i].id])
            continue
        var cnt = 1
        var a = sortedA[i].A
        var b = sortedA[i].B
        var idxA = i + 1
        var idxB = i
        selected[sortedA[i].id] = true
        while ((idxA < N && sortedA[idxA].A <= a) || (idxB < N && sortedB[idxB].B <= b)) {
            if (idxA < N && sortedA[idxA].A <= a) {
                if (!selected[sortedA[idxA].id]) {
                    selected[sortedA[idxA].id] = true
                    cnt++
                }
                b = max(b, sortedA[idxA].B)
                idxA++
            }
            if (idxB < N && sortedB[idxB].B <= b) {
                if (!selected[sortedB[idxB].id]) {
                    selected[sortedB[idxB].id] = true
                    cnt++
                }
                a = max(a, sortedB[idxB].A)
                idxB++
            }
        }
        deque.add(cnt)
    }
    var a = 0
    var b = N
    var c = 0
    var ans = Int.MAX_VALUE
    if (deque.size < 3)
        ans = -1
    while (deque.isNotEmpty()) {
        if (a < c) {
            val temp = deque.removeFirst()
            a += temp
            b -= temp
        } else {
            val temp = deque.removeLast()
            c += temp
            b -= temp
        }
        ans = min(ans, maxOf(a, b, c) - minOf(a, b, c))
    }
    writer.write("${ans}\n")
    writer.flush()
}
    