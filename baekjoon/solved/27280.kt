package `27280`

import java.io.BufferedReader
import java.io.InputStreamReader

data class Data(var height: Long, var sum: Long) {
    operator fun compareTo(other: Data): Int = when {
        sum > other.sum -> 1
        sum < other.sum -> -1
        else -> 0
    }
}

fun max(a: Data, b: Data): Data = if (a > b) a else b

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val dp = Array<Array<Data>>(N + 1) { Array(M + 1) { Data(0, 0) } }
    val arr = Array<Data>(N + 1) { Data(0, 0) }
    repeat(N) {
        val (h, p) = reader.readLine().split(" ").map { it.toLong() }
        arr[it + 1] = Data(h, p)
    }
    // 그룹
    for (g in 1..M) {
        // 병사
        for (i in 1..N) {
            // i부터 g까지 그룹으로 묶었을 때.
            val data = Data(0, 0)
            for (j in i downTo g) {
                if (data.height == arr[j].height) {
                    data.sum += arr[j].sum
                } else if (data.height < arr[j].height) {
                    data.height = arr[j].height
                    data.sum = arr[j].sum
                }
                // i부터 j까지를 새그룹으로 만들었으므로 dp[j-1][g-1]와 비교
                if (dp[i][g].sum < dp[j - 1][g - 1].sum + data.sum)
                    dp[i][g] = Data(data.height, data.sum + dp[j - 1][g - 1].sum)
                if (g == 1)
                    dp[i][g] = data
            }
        }
    }
    println(dp[N][M].sum)
}