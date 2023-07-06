package `2467`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    var res = Int.MAX_VALUE
    var s = -1
    var e = -1
    for (i in 0 until N - 1) {
        var left = i + 1
        var right = arr.size
        while (left <= right) {
            val mid = (left + right) / 2
            if (mid !in 0 until N)
                break
            if (abs(arr[mid] + arr[i]) < res) {
                res = abs(arr[mid] + arr[i])
                s = i
                e = mid
            }
            if (arr[mid] + arr[i] < 0)
                left = mid + 1
            else right = mid - 1
        }
    }
    writer.write("${arr[s]} ${arr[e]}")
    writer.flush()
}