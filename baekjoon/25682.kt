package `25682`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val board = Array<Array<Int>>(N + 1) { Array(M + 1) { 0 } }
    repeat(N) { y ->
        val line = reader.readLine()
        line.forEachIndexed { x, c ->
            board[y + 1][x + 1] = board[y + 1][x] + board[y][x + 1] - board[y][x]
            if (c == 'B' && (y + x) % 2 == 0 || c == 'W' && (y + x) % 2 == 1)
                board[y + 1][x + 1]++
        }
    }
    // K * K - 구간합 -> WB 순서 바꾼 값
    // min(구간합, K * K - 구간합)
    var result = Int.MAX_VALUE
    val size = K * K
    for (y in K..N) {
        for (x in K..M) {
            val sum = board[y][x] - board[y - K][x] - board[y][x - K] + board[y - K][x - K]
            result = min(min(result, sum), size - sum)
        }
    }
    println(result)
}