package `2170`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max


class Point(val start: Long, val end: Long) : Comparable<Point> {
    override fun compareTo(other: Point): Int {
        return if (start == other.start) {
            if (end == other.end) {
                return 0
            } else if (end > other.end)
                return 1
            else
                return -1
        } else if (start > other.start)
            1
        else -1
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val arr = Array<Point>(N) { Point(0, 0) }
    repeat(N) {
        val (start, end) = reader.readLine().split(" ").map { it.toLong() }
        arr[it] = Point(start, end)
    }
    arr.sort()
    var end = arr[0].end
    var sum = end - arr[0].start

    for (i in 1 until N) {
        val now = arr[i]
        if (now.start > end) {
            sum += now.end - now.start
        } else if (now.end > end){
            sum += now.end - end
        }
        end = max(end, now.end)
    }
    println(sum)
}
