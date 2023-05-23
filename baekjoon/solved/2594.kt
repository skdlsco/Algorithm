package `2594`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun timeToMinute(time: String): Int {
    return time.slice(0..1).toInt() * 60 + time.slice(2..3).toInt()
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    var result = 0
    val times = ArrayList<Pair<Int, Int>>()

    repeat(N) {
        val (start, end) = reader.readLine().split(" ")
        val startTime = timeToMinute(start) - 10
        val endTime = timeToMinute(end) + 10

        times.add(Pair(startTime, endTime))
    }
    times.add(Pair(timeToMinute("2200"), timeToMinute("2200")))
    times.sortBy { it.first }
    var start = 600
    var end = 600
    for ((s, e) in times) {
        if (s > end)
            result = max(result, s - end)
        if (start > e)
            result = max(result, start - e)
        start = s
        if (e > end)
            end = e
    }
    writer.write("${result}")
    writer.flush()
}