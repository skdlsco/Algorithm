package `28813`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.max

data class Data(val x: Int, val y: Int, val idx: Int) {
    val distance = max(abs(x), abs(y))
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = ArrayList<Data>(N)
    repeat(N) {
        val (x, y) = reader.readLine().split(" ").map { it.toInt() }
        arr.add(Data(x, y, it + 1))
    }
    arr.sortBy { it.distance }
    var cnt = 0
    for (d in arr) {
        if (cnt >= d.distance) {
            cnt = -1
            break
        }
        cnt++
    }

    if (cnt == -1)
        writer.write("${-1}\n")
    else
        writer.write(arr.joinToString(" ") { "${it.idx}" })
    writer.flush()
}
    