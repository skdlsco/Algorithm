package `2343`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }

    var left = 0
    var right = 1000000000
    while (left < right) {
        val mid = (left + right) / 2
        var cnt = 0
        var k = 0
        var isFailed = false
        arr.forEach {
            if (it > mid)
                isFailed = true
            if (k < it) {
                cnt++
                k = mid
            }
            k -= it
        }
        if (isFailed || cnt > M) {
            left = mid + 1
        } else {
            right = mid
        }
    }
    println(left)
}