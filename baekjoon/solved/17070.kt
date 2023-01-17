package `17070`

fun main() {
    val N = readLine()!!.toInt()
    val dp = Array<Array<IntArray>>(3) { Array(N + 1) { IntArray(N) { 0 } } }
    val map = Array<Array<Boolean>>(N + 1) { Array(N) { true } }
    repeat(N) {
        map[it + 1] = readLine()!!.split(" ").map { it == "0" }.toTypedArray()
    }
    dp[0][1][1] = 1
    (1..N).forEach { y ->
        (2 until N).forEach { x ->
            if (!map[y][x]) {
                dp[0][y][x] = 0
                dp[1][y][x] = 0
                dp[2][y][x] = 0
            } else {
                if (!map[y - 1][x] || !map[y][x - 1])
                    dp[2][y][x] = 0
                else
                    dp[2][y][x] = dp[0][y - 1][x - 1] + dp[1][y - 1][x - 1] + dp[2][y - 1][x - 1]
                dp[1][y][x] = dp[2][y - 1][x] + dp[1][y - 1][x]
                dp[0][y][x] = dp[0][y][x - 1] + dp[2][y][x - 1]
            }
        }
    }
    println(dp.sumOf { it[N][N - 1] })
}