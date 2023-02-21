package `2339`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.StringTokenizer

const val JEWEL = 2
const val IMPURITIES = 1
const val HORIZONTAL = 0
const val VERTICAL = 1

// 보드, 자르는 방향, 현재 대상 영역
fun solve(board: Array<Array<Int>>, direction: Int, y1: Int, x1: Int, y2: Int, x2: Int): Int {
    var jewel = 0
    var impurities = 0
    for (y in y1 until y2) {
        for (x in x1 until x2) {
            when (board[y][x]) {
                JEWEL -> jewel++
                IMPURITIES -> impurities++
            }
        }
    }
    if (jewel == 0)
        return 0
    if (impurities == 0)
        return if (jewel == 1) 1 else 0
    var sum = 0
    // 가로 방향
    if (direction == HORIZONTAL) {
        // y만 변화
        for (y in y1 + 1 until y2 - 1) {
            // 불순물이 있고 jewel이 없는 경우 진행
            val existImpurities = (x1 until x2).any { board[y][it] == IMPURITIES }
            val existJewel = (x1 until x2).any { board[y][it] == JEWEL }
            if (!existJewel && existImpurities) {
                // 위
                // 아래
                sum += solve(board, VERTICAL, y1, x1, y, x2) * solve(board, VERTICAL, y + 1, x1, y2, x2)
            }
        }
    }
    if (direction == VERTICAL) {
        // x만 변화
        for (x in x1 + 1 until x2 - 1) {
            // 불순물이 있고 jewel이 없는 경우 진행
            val existImpurities = (y1 until y2).any { board[it][x] == IMPURITIES }
            val existJewel = (y1 until y2).any { board[it][x] == JEWEL }
            if (!existJewel && existImpurities) {
                // 오른쪽
                // 왼쪽
                sum += solve(board, HORIZONTAL, y1, x1, y2, x) * solve(board, HORIZONTAL, y1, x + 1, y2, x2)
            }
        }
    }
    return sum
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val board = Array<Array<Int>>(N) { Array(N) { 0 } }
    var jewel = 0
    var impurities = 0
    for (y in 0 until N) {
        val stringTokenizer = StringTokenizer(reader.readLine())
        for (x in 0 until N) {
            val token = stringTokenizer.nextToken().toInt()
            when (token) {
                JEWEL -> jewel++
                IMPURITIES -> impurities++
            }
            board[y][x] = token
        }
    }
    if (jewel == 1 && impurities == 0) {
        println(1)
        return
    }
    val result = solve(board, HORIZONTAL, 0, 0, N, N) + solve(board, VERTICAL, 0, 0, N, N)
    if (result == 0)
        println(-1)
    else
        println(result)
}
