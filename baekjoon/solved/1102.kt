package `1102`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer
import kotlin.math.min

val MAX_VALUE = 123456789
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    // i, j -> i에서 j로 가는 비용
    val map = Array<Array<Int>>(N) { Array(N) { 0 } }
    // i가 켜져있는지
    val on = BooleanArray(N) { false }
    for (i in 0 until N) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        for (j in 0 until N) {
            map[i][j] = stringTokenizer.nextToken().toInt()
        }
    }
    reader.readLine().forEachIndexed { i, c ->
        if (c == 'Y') {
            on[i] = true
        }
    }
    val dp = Array(1 shl N) { MAX_VALUE }
    dp[0] = 0
    for (i in 0 until (1 shl N)) {
        for (j in 0 until N) {
            // 발전소 j를 킨다.
            val next = i or (1 shl j)
            // j가 처음에 켜져있던 경우 비용이 없으므로
            if (on[j])
                dp[next] = min(dp[next], dp[i])
            for (k in 0 until N) {
                // map에서 k to j
                if (i and (1 shl k) > 0)
                    dp[next] = min(dp[next], dp[i] + map[k][j])
            }
        }
    }
    val target = reader.readLine().toInt()
    var result = MAX_VALUE
    for (i in 0 until (1 shl N)) {
        var cnt = 0
        for (j in 0 until N) {
            if (i and (1 shl j) > 0)
                cnt++
        }
        if (cnt >= target)
            result = min(result, dp[i])
    }
    if (result == MAX_VALUE)
        println(-1)
    else
        println(result)
}