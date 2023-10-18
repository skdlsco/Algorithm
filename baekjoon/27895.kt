package `27895`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

const val MOD = 1_000_000_007

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (M, N, K) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Char>>(N) { Array(M) { ' ' } }
    for (y in 0 until N) {
        val row = reader.readLine()
        for (x in 0 until M) {
            map[y][x] = row[x]
        }
    }
    val up = Array<Array<Long>>(N) { Array(M) { 0 } }
    up[N - 1][M - 1] = 1
    for (y in N - 1 downTo 0) {
        for (x in M - 1 downTo 0) {
            if (y + 1 in 0 until N)
                up[y][x] = (up[y + 1][x] + up[y][x]) % MOD
            if (x + 1 in 0 until M)
                up[y][x] = (up[y][x + 1] + up[y][x]) % MOD
            if (map[y][x] == '1')
                up[y][x] = 0
        }
    }
    val down = Array<Array<Long>>(N) { Array(M) { 0 } }
    down[0][0] = 1
    for (y in 0 until N) {
        for (x in 0 until M) {
            if (y - 1 in 0 until N)
                down[y][x] = (down[y][x] + down[y - 1][x]) % MOD
            if (x - 1 in 0 until M)
                down[y][x] = (down[y][x] + down[y][x - 1]) % MOD
            if (map[y][x] == '1')
                down[y][x] = 0
        }
    }
    var sum = up[0][0]
    repeat(K) {
        val (x1, y1, x2, y2) = reader.readLine().split(" ").map { it.toInt() }
        // 1 -> 2가 단순 이동과 다른 경우
        if ((x1 + 1 != x2 || y1 != y2) && (x1 != x2 || y1 + 1 != y2)) {
            sum = ((sum + (down[y1][x1] * up[y2][x2]) % MOD) % MOD)
        }
        if ((x2 + 1 != x2 || y2 != y1) && (x1 != x2 || y2 + 1 != y1)) {
            sum = ((sum + (down[y2][x2] * up[y1][x1]) % MOD) % MOD)
        }
    }
    writer.write("${sum}\n")
    writer.flush()
}
