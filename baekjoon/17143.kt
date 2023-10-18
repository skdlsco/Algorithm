package `17143`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

data class Shark(val s: Int, val z: Int, val dy: Int, val dx: Int, var p: Int)

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (R, C, M) = reader.readLine().split(" ").map { it.toInt() }
    var board = Array<Array<Shark?>>(R) { Array(C) { null } }
    val row = (0 until R).plus((R - 2 downTo 1))
    val column = (0 until C).plus((C - 2 downTo 1))
    repeat(M) {
        var (r, c, s, d, z) = reader.readLine().split(" ").map { it.toInt() }
        if (d == 1 && (r == 0 || r == R))
            d = 2
        if (d == 4 && (c == C || c == 0))
            d = 3
        val dy = if (d < 3) 1 else 0
        val dx = if (d < 3) 0 else 1
        // 시작 위치
        val p = when (d) {
            1 -> R + R - r - 1
            2 -> r - 1
            3 -> c - 1
            // 4
            else -> C + C - c - 1
        }
        board[r - 1][c - 1] = Shark(s, z, dy, dx, p)
    }
    var sum = 0
    for (i in 0 until C) {
        // 낚시 시작
        for (j in 0 until R) {
            if (board[j][i] == null)
                continue
            sum += board[j][i]!!.z
            board[j][i] = null
            break
        }
        val nextBoard = Array<Array<Shark?>>(R) { Array(C) { null } }
        for (y in 0 until R) {
            for (x in 0 until C) {
                if (board[y][x] == null)
                    continue
                val shark = board[y][x]!!
                val ny = if (shark.dy == 1) {
                    val np = (shark.p + (shark.s % ((R - 1) * 2))) % row.size
                    shark.p = np
                    row[np]
                } else y
                val nx = if (shark.dx == 1) {
                    val np = (shark.p + (shark.s % ((C - 1) * 2))) % column.size
                    shark.p = np
                    column[np]
                } else x
                if ((nextBoard[ny][nx]?.z ?: 0) < shark.z)
                    nextBoard[ny][nx] = shark
            }
        }
        board = nextBoard
    }
    writer.write("${sum}\n")
    writer.flush()
}