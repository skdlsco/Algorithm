package `16973`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList
import java.util.StringTokenizer

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val map = Array<Array<Int>>(N + 1) { Array(M + 1) { 0 } }
    for (y in 1..N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        for (x in 1..M) {
            map[y][x] += row[x - 1]
            map[y][x] += map[y][x - 1]
            map[y][x] += map[y - 1][x]
            map[y][x] -= map[y - 1][x - 1]
        }
    }
    val st = StringTokenizer(reader.readLine())
    val H = st.nextToken().toInt()
    val W = st.nextToken().toInt()
    val sY = st.nextToken().toInt()
    val sX = st.nextToken().toInt()
    val eY = st.nextToken().toInt()
    val eX = st.nextToken().toInt()

    val visited = Array<Array<Boolean>>(N + 1) { Array(M + 1) { false } }
    val queue = LinkedList<Pair<Int, Pair<Int, Int>>>()
    queue.add(Pair(0, Pair(sX, sY)))
    visited[sY][sX] = true
    var ans = -1
    while (queue.isNotEmpty()) {
        val (curDistance, curPos) = queue.pop()
        val (cX, cY) = curPos
        if (cX == eX && cY == eY) {
            ans = curDistance
            break
        }
        for (i in 0 until 4) {
            val nX = cX + dx[i]
            val nY = cY + dy[i]
            if (nY in 1..N && nX in 1..M &&
                nY + H - 1 in 1..N && nX + W - 1 in 1..M && !visited[nY][nX]
            ) {
                visited[nY][nX] = true
                val obstacle = map[nY + H - 1][nX + W - 1] + map[nY - 1][nX - 1] -
                        map[nY + H - 1][nX - 1] - map[nY - 1][nX + W - 1]
                if (obstacle == 0)
                    queue.add(Pair(curDistance + 1, Pair(nX, nY)))
            }
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}
    