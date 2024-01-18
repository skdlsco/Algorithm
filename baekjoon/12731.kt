import java.util.*

val reader = System.`in`.bufferedReader()
val writer = System.out.bufferedWriter()

data class Data(val time: Int, val diff: Int, val type: Int)

fun toTime(data: String): Int {
    val d = data.split(":")
    return d[0].toInt() * 60 + d[1].toInt()
}

fun solve(): Pair<Int, Int> {
    val T = reader.readLine().toInt()
    val (NA, NB) = reader.readLine().split(" ").map { it.toInt() }
    val pq = PriorityQueue<Data>() { v1, v2 ->
        if (v1.time == v2.time)
            v2.diff.compareTo(v1.diff)
        else
            v1.time.compareTo(v2.time)
    }
    repeat(NA) {
        val data = reader.readLine().split(" ")
        pq.add(Data(toTime(data[0]), -1, 0))
        pq.add(Data(toTime(data[1]) + T, 1, 1))
    }
    repeat(NB) {
        val data = reader.readLine().split(" ")
        pq.add(Data(toTime(data[0]), -1, 1))
        pq.add(Data(toTime(data[1]) + T, 1, 0))
    }
    var ansA = 0
    var ansB = 0
    var A = 0
    var B = 0
    while (pq.isNotEmpty()) {
        val (time, diff, type) = pq.remove()
        if (type == 0) {
            A += diff
            if (A < 0) {
                ansA++
                A++
            }
        } else {
            B += diff
            if (B < 0) {
                ansB++
                B++
            }
        }
    }
    return Pair(ansA, ansB)
}

fun main() {
    val T = reader.readLine().toInt()
    repeat(T) {
        val ans = solve()
        writer.write("Case #${it + 1}: ${ans.first} ${ans.second}\n")
    }

    writer.flush()
}

