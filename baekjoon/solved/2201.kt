package `2201`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    var K = reader.readLine().toLong()
    if (K <= 2) {
        print("1")
        if (K == 2L)
            print("0")
        return
    }
    val result = StringBuilder()
    // N자리 수에서의 이친수.
    val dp = ArrayList<Long>()
    dp.add(0)
    dp.add(1)
    dp.add(1)
    var sum = 2L
    var i = 2
    while (sum < K) {
        dp.add(dp[i] + dp[i - 1])
        sum += dp.last()
        i++
    }
    var length = -1
    while (true) {
        sum = 0L
        i = 0
        while (sum < K) {
            i++
            sum += dp[i]
        }
        if (length == -1)
            length = i
        // 이전 자리수에서 현재 자리수까지의 사이를 0으로 채우기
        repeat(length - i) {
            result.append('0')
        }
        if (i == 0)
            break
        K -= sum - dp[i] + 1
        result.append('1')
        length = i - 1
    }
    println(result.toString())
}