package `6236`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array(N) { reader.readLine().toInt() }
    var left = 0
    var right = 1000000001
    while (left < right) {
        val mid = (left + right) / 2
        var money = 0
        var cnt = 0
        var isPossible = true
        arr.forEach {
            if (it > mid)
                isPossible = false
            if (it > money) {
                money = mid
                cnt++
            }
            money -= it
        }
        if (!isPossible || cnt > M)
            left = mid + 1
        else
            right = mid
    }
    println(right)
}