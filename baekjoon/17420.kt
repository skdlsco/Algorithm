package `17420`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.ceil
import kotlin.math.max

data class Data(var a: Int, val b: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val A = reader.readLine().split(" ").map { it.toInt() }
    val B = reader.readLine().split(" ").map { it.toInt() }
    val dataArr = Array<Data>(N) { Data(A[it], B[it]) }
    dataArr.sortBy { it.b }
    var result = 0L

    var minA = 0
    var maxA = 0
    var curB = 0
    for (i in 0 until N) {
        val data = dataArr[i]
        if (curB < data.b) {
            minA = maxA
            curB = data.b
        }
        val target = max(data.b, minA)
        if (target > data.a) {
            val cnt = ceil((target - data.a) / 30.0).toInt()
            data.a += cnt * 30
            result += cnt
        }
        maxA = max(maxA, data.a)
    }
    writer.write("${result}")
    writer.flush()
}