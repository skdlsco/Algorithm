package D

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val board = Array<Array<Char>>(N) { Array(M) { 'A' } }
    for (y in 0 until N) {
        val row = reader.readLine()
        for (x in 0 until M) {
            board[y][x] = row[x]
        }
    }
    // count
    // y, x, c = cnt
    val tile = Array<Array<Array<Int>>>(K) { Array(K) { Array(26) { 0 } } }
    for (y in 0 until N) {
        for (x in 0 until M) {
            val cellY = y % K
            val cellX = x % K
            val c = board[y][x].code - 'A'.code
            tile[cellY][cellX][c]++
        }
    }
    val newTile = Array<Array<Char>>(K) { Array(K) { ' ' } }
    for (y in 0 until K) {
        for (x in 0 until K) {
            var maxIdx = 0
            for (i in 0 until 26) {
                if (tile[y][x][i] > tile[y][x][maxIdx])
                    maxIdx = i
            }
            newTile[y][x] = (maxIdx + 'A'.code).toChar()
        }
    }
    var cnt = 0
    for (y in 0 until N) {
        for (x in 0 until M) {
            val cellY = y % K
            val cellX = x % K
            if (board[y][x] != newTile[cellY][cellX])
                cnt++
        }
    }
    writer.write("${cnt}\n")
    for (y in 0 until N) {
        for (x in 0 until M) {
            val cellY = y % K
            val cellX = x % K
            writer.write("${newTile[cellY][cellX]}")
        }
        writer.newLine()
    }
    writer.flush()
}