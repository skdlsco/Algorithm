package `27921`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

const val MAXH = 10
const val MAXW = 10

val start = Array<Array<Char>>(MAXH) { Array(MAXW) { '.' } }
val end = Array<Array<Char>>(MAXH) { Array(MAXW) { '.' } }

fun matchCoin(startX: Int, startY: Int, endX: Int, endY: Int): Int {
    var cnt = 0
    for (y in 0 until MAXH) {
        for (x in 0 until MAXW) {
            if (y + startY in 0 until MAXH && x + startX in 0 until MAXW &&
                y + endY in 0 until MAXH && x + endX in 0 until MAXW
            ) {
                if (start[y + startY][x + startX] == 'O' && end[y + endY][x + endX] == 'O')
                    cnt++
            }
        }
    }
    return cnt
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val (H1, W1) = reader.readLine().split(" ").map { it.toInt() }
    var coinTotal = 0
    for (y in 0 until H1) {
        val line = reader.readLine()
        for (x in 0 until W1) {
            start[y][x] = line[x]
            if (line[x] == 'O')
                coinTotal++
        }
    }
    val (H2, W2) = reader.readLine().split(" ").map { it.toInt() }
    for (y in 0 until H2) {
        val line = reader.readLine()
        for (x in 0 until W2) {
            end[y][x] = line[x]
        }
    }
    var result = coinTotal
    for (startY in -10 until MAXH) {
        for (startX in -10 until MAXW) {
            for (endY in -10 until MAXH) {
                for (endX in -10 until MAXW) {
                    val matchCoinCount = matchCoin(startX, startY, endX, endY)
                    val diffCoinCount = coinTotal - matchCoinCount
                    result = min(diffCoinCount, result)
                }
            }
        }
    }
    writer.write("${result}\n")
    writer.flush()
}