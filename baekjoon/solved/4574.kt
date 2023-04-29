package `4574`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

class Sudominoku(val board: Array<Array<Int>>, val usedDominos: Array<Array<Boolean>>) {

    private fun isExistInVertical(x: Int, number: Int): Boolean {
        return (0 until 9).any { board[it][x] == number }
    }

    private fun isExistInHorizontal(y: Int, number: Int): Boolean {
        return (0 until 9).any { board[y][it] == number }
    }

    private fun isExistInCell(y: Int, x: Int, number: Int): Boolean {
        val sy = y - y % 3
        val sx = x - x % 3
        for (cy in 0 until 3) {
            for (cx in 0 until 3) {
                if (board[sy + cy][sx + cx] == number)
                    return true
            }
        }
        return false
    }

    private fun isValidNumber(y: Int, x: Int, number: Int): Boolean {
        return !(isExistInVertical(x, number) || isExistInHorizontal(y, number) || isExistInCell(y, x, number))
    }

    private fun backtracking(cur: Int): Boolean {
        val pos = findNextPos(cur)
        if (pos == -1)
            return true
        val y = pos / 9
        val x = pos % 9
        for (i in 1..9) {
            if (board[y][x] != 0 || !isValidNumber(y, x, i))
                continue
            board[y][x] = i
            for (j in 1..9) {
                if (usedDominos[i][j])
                    continue
                usedDominos[i][j] = true
                usedDominos[j][i] = true
                if (x < 8 && board[y][x + 1] == 0 && isValidNumber(y, x + 1, j)) {
                    board[y][x + 1] = j
                    if (backtracking(pos))
                        return true
                    board[y][x + 1] = 0
                }
                if (y < 8 && board[y + 1][x] == 0 && isValidNumber(y + 1, x, j)) {
                    board[y + 1][x] = j
                    if (backtracking(pos))
                        return true
                    board[y + 1][x] = 0
                }
                usedDominos[i][j] = false
                usedDominos[j][i] = false
            }
            board[y][x] = 0
        }
        return false
    }

    fun findNextPos(start: Int): Int {
        for (next in start until 81) {
            val nextY = next / 9
            val nextX = next % 9
            if (board[nextY][nextX] == 0)
                return next
        }
        return -1
    }

    fun solve() {
        backtracking(0)
    }

    override fun toString(): String {
        return board.joinToString("\n") { it.joinToString("") }
    }
}

fun chessPosToPair(pos: String): Pair<Int, Int> {
    return Pair(pos[0].code - 'A'.code, pos[1].code - '1'.code)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    var puzzleNum = 1
    while (true) {
        val initDominoCount = reader.readLine().toInt()
        if (initDominoCount == 0)
            break

        val board = Array<Array<Int>>(9) { Array(9) { 0 } }
        val dominos = Array<Array<Boolean>>(10) { Array(10) { false } }
        for (i in 1..9) {
            dominos[i][i] = true
        }
        repeat(initDominoCount) {
            val stringTokenizer = StringTokenizer(reader.readLine())
            val a = stringTokenizer.nextToken().toInt()
            val aPos = chessPosToPair(stringTokenizer.nextToken())
            val b = stringTokenizer.nextToken().toInt()
            val bPos = chessPosToPair(stringTokenizer.nextToken())
            board[aPos.first][aPos.second] = a
            board[bPos.first][bPos.second] = b
            dominos[a][b] = true
            dominos[b][a] = true
        }
        val positions = reader.readLine().split(" ")
        for (i in 0 until 9) {
            val (y, x) = chessPosToPair(positions[i])
            board[y][x] = i + 1
        }

        val sudominoku = Sudominoku(board, dominos)
        sudominoku.solve()
        writer.write("Puzzle ${puzzleNum}\n")
        writer.write(sudominoku.toString())
        writer.newLine()
        puzzleNum++
    }
    writer.flush()
}