package `14595`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

data class Data(val x: Int, val y: Int) {
    constructor(d: List<Int>) : this(d[0], d[1])
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val M = reader.readLine().toInt()
    val action = Array<Data>(M) { Data(reader.readLine().split(" ").map { it.toInt() }) }

    var right = 0
    var cnt = 0
    action.sortBy { it.x }
    for (cur in action) {
        if (cur.x > right) {
            cnt += cur.x - right
        }
        right = max(right, cur.y)
    }
    cnt += N - right
    writer.write("${cnt}\n")
    writer.flush()
}