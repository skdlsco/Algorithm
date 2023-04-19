package `14503`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

val dx = arrayOf(0, 1, 0, -1)
val dy = arrayOf(-1, 0, 1, 0)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    var (r, c, d) = reader.readLine().split(" ").map { it.toInt() }
    val wallMap = Array<Array<Boolean>>(N) { Array(M) { false } }
    val visitedMap = Array<Array<Boolean>>(N) { Array(M) { false } }
    for (y in 0 until N) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        for (x in 0 until M) {
            val cur = stringTokenizer.nextToken().toInt()
            if (cur == 1) {
                wallMap[y][x] = true
                visitedMap[y][x] = true
            }
        }
    }
    var cnt = 0
    while (true) {
        val isClear = (0 until 4).none { !visitedMap[r + dy[it]][c + dx[it]] }
        if (!visitedMap[r][c]) {
            visitedMap[r][c] = true
            cnt++
        } else if (isClear) {
            val backDirection = (d + 2) % 4
            if (!wallMap[r + dy[backDirection]][c + dx[backDirection]]) {
                r += dy[backDirection]
                c += dx[backDirection]
            } else
                break
        } else {
            d = (4 + d - 1) % 4
            if (!visitedMap[r + dy[d]][c + dx[d]]) {
                r += dy[d]
                c += dx[d]
            }
        }
    }
    writer.write("${cnt}\n")
    writer.flush()
}