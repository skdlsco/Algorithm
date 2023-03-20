package `2038`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val dp = ArrayList<Long>()
    dp.add(0)
    dp.add(1)
    var j = 2
    while (dp.last() < N) {
        dp.add(dp.last() + j)
        if (dp.size > dp[j])
            j++
    }
    println(dp.lastIndex)
}

