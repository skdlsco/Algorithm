package `2642`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.*


enum class Face(val num: Int) {
    U(0), D(1), R(2), L(3), F(4), B(5)
}

// u d r l
val deltaFace = arrayOf(
    arrayOf(Face.B.num, Face.F.num, Face.R.num, Face.L.num, Face.U.num, Face.D.num),
    arrayOf(Face.F.num, Face.B.num, Face.R.num, Face.L.num, Face.D.num, Face.U.num),
    arrayOf(Face.U.num, Face.D.num, Face.B.num, Face.F.num, Face.R.num, Face.L.num),
    arrayOf(Face.U.num, Face.D.num, Face.F.num, Face.B.num, Face.L.num, Face.R.num)
)

// u d r l
val dx = arrayOf(0, 0, 1, -1)
val dy = arrayOf(-1, 1, 0, 0)

fun getStartPoint(map: Array<Array<Int>>): Pair<Int, Int> {
    for (y in 0 until 6) {
        for (x in 0 until 6) {
            if (map[y][x] == 1)
                return Pair(y, x)
        }
    }
    return Pair(0, 0)
}

data class Data(val y: Int, val x: Int, val faces: Array<Int>)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val map = Array<Array<Int>>(6) { reader.readLine().split(" ").map { it.toInt() }.toTypedArray() }
    // udrlfb
    val dice = Array<Int>(6) { 0 }
    val visited = Array<Array<Boolean>>(6) { Array(6) { false } }
    val start = getStartPoint(map)
    val queue = LinkedList<Data>()
    queue.add(Data(start.first, start.second, arrayOf(0, 1, 2, 3, 4, 5)))
    visited[start.first][start.second] = true
    dice[Face.F.num] = map[start.first][start.second]
    while (queue.isNotEmpty()) {
        val (y, x, face) = queue.pop()

        for (i in 0 until 4) {
            val nextY = dy[i] + y
            val nextX = dx[i] + x
            val nextFace = deltaFace[i].map { face[it] }.toTypedArray()
            if (nextY in 0 until 6 && nextX in 0 until 6
                && !visited[nextY][nextX] && map[nextY][nextX] != 0
            ) {
                queue.add(Data(nextY, nextX, nextFace))
                dice[nextFace[Face.F.num]] = map[nextY][nextX]
                visited[nextY][nextX] = true
            }
        }
    }
    if (dice.all { it != 0 }) {
        val oneIdx = dice.indexOf(1)
        writer.write("${if (oneIdx % 2 == 0) dice[oneIdx + 1] else dice[oneIdx - 1]}")
    } else
        writer.write("0")
    writer.flush()
}
