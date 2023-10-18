package `1520`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val dx = arrayOf(1, -1, 0, 0)
val dy = arrayOf(0, 0, -1, 1)
fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (M, N) = reader.readLine().split(" ").map { it.toInt() }
    val dp = Array<Array<Int>>(M) { Array(N) { 0 } }
    val cells = ArrayList<Pair<Int, Pair<Int, Int>>>()
    val arr = Array<Array<Int>>(M) { Array(N) { 0 } }
    for (y in 0 until M) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 0 until N) {
            arr[y][x] = row[x]
            cells.add(Pair(row[x], Pair(x, y)))
        }
    }

    cells.sortByDescending { it.first }
    dp[0][0] = 1
    for (cell in cells) {
        val (_, pos) = cell
        val (x, y) = pos
        for (i in 0 until 4) {
            val pX = x + dx[i]
            val pY = y + dy[i]
            if (pX in 0 until N && pY in 0 until M && arr[pY][pX] > arr[y][x]) {
                dp[y][x] += dp[pY][pX]
            }
        }
    }
    writer.write("${dp[M - 1][N - 1]}\n")
    writer.flush()
}