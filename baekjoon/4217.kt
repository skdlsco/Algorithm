import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.ArrayList
import java.util.LinkedList

data class Pos(val x: Int, val y: Int)

val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(1, -1, 0, 0)

fun toInt(c: Char): Int {
    return if (c in "0123456789")
        c.code - '0'.code
    else
        10 + c.code - 'a'.code
}

fun checkBlacks(H: Int, W: Int, map: Array<Array<Boolean>>, dist: Array<Array<Int>>): Int {
    var num = 0
    val visited = Array<Array<Boolean>>(H) { Array(W) { false } }
    val queue = LinkedList<Pos>()
    for (y in 0 until H) {
        for (x in 0 until W) {
            if (visited[y][x] || !map[y][x])
                continue
            num++
            queue.add(Pos(x, y))
            visited[y][x] = true
            while (queue.isNotEmpty()) {
                val (cx, cy) = queue.pop()
                dist[cy][cx] = num
                for (i in 0 until 4) {
                    val nx = cx + dx[i]
                    val ny = cy + dy[i]
                    if (nx in 0 until W && ny in 0 until H &&
                            !visited[ny][nx] && map[ny][nx]) {
                        visited[ny][nx] = true
                        queue.add(Pos(nx, ny))
                    }
                }
            }
        }
    }
    return num
}

fun checkWhites(H: Int, W: Int, map: Array<Array<Boolean>>, artMap: Array<Array<Int>>, artStatus: Array<Int>) {
    val visited = Array<Array<Boolean>>(H) { Array(W) { false } }
    val queue = LinkedList<Pos>()
    for (y in 0 until H) {
        for (x in 0 until W) {
            if (visited[y][x] || map[y][x])
                continue
            // 0 = wall
            val checkedArt = Array<Boolean>(artStatus.size) { false }
            queue.add(Pos(x, y))
            visited[y][x] = true
            while (queue.isNotEmpty()) {
                val (cx, cy) = queue.pop()
                for (i in 0 until 4) {
                    val nx = cx + dx[i]
                    val ny = cy + dy[i]
                    if (nx in 0 until W && ny in 0 until H) {
                        if (map[ny][nx]) {
                            checkedArt[artMap[ny][nx]] = true
                        } else if (!visited[ny][nx]) {
                            visited[ny][nx] = true
                            queue.add(Pos(nx, ny))
                        }
                    } else {
                        checkedArt[0] = true
                    }
                }
            }
            if (checkedArt.count { it } == 1) {
                val artNum = checkedArt.indexOf(true)
                artStatus[artNum]++
            }
        }
    }
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var case = 1
    while (true) {
        val (H, W) = reader.readLine().split(" ").map { it.toInt() }
        if (H == 0 && W == 0)
            break
        val map = Array<Array<Boolean>>(H) { Array(W * 4) { false } }
        for (y in 0 until H) {
            val line = reader.readLine()
            for (x in 0 until W * 4) {
                val v = toInt(line[x / 4])
                map[y][x] = v and (1 shl (3 - x % 4)) > 0
            }
        }
        val artMap = Array<Array<Int>>(H) { Array(W * 4) { 0 } }
        // 검은색에 넘버링 부여 및 bfs돌려서 전부 해당 숫자로 맞춘다.
        val artCount = checkBlacks(H, W * 4, map, artMap)
        val artStatus = Array<Int>(artCount + 1) { 0 }
        // 이후 흰색이 나오면 bfs 주변에 닿은 검은색 넘버링이 하나면 해당 글자, 벽이나 2개면 공백
        checkWhites(H, W * 4, map, artMap, artStatus)
        val ans = ArrayList<Char>()
        for (i in 1..artCount) {
            ans.add("WAKJSD"[artStatus[i]])
        }
        ans.sort()
        writer.write("Case ${case}: ${ans.joinToString("")}\n")
        case++
    }
    writer.flush()
}
