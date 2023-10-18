package `1576`

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

const val base = "ACGT"

fun makeXXComb(): List<Array<Int>> {
    val comb = ArrayList<Array<Int>>()
    for (i in 1..10) {
        for (j in 1..10) {
            for (k in 1..10) {
                for (l in 1..10) {
                    comb.add(arrayOf(i, j, k, l))
                }
            }
        }
    }
    return comb
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val dnaArr = Array<String>(N) { "" }
    val board = HashMap<String, Int>()
    repeat(N) {
        dnaArr[it] = reader.readLine()
    }
    val dnaLen = dnaArr[0].length
    for (x in base.indices) {
        for (y in x until base.length)
            board["${base[x]}${base[y]}"] = 0
    }
    for (i in 0 until N - 1) {
        for (j in i + 1 until N) {
            for (k in 0 until dnaLen) {
                val x = dnaArr[i][k]
                val y = dnaArr[j][k]
                val cell = if (x.code > y.code) "$y$x"
                else "$x$y"
                board[cell] = board[cell]!! + 1
            }
        }
    }
    val comb = makeXXComb()
    val list = board.toList().sortedByDescending { it.second }
    var result = -100000000.0
    for (i in comb.indices) {
        val xxSum = comb[i].sumOf { it }
        if (xxSum % 2 == 1)
            continue
        var scoreSum = 0.0
        var xx = 0
        var xy = 0
        val xy3 = (20 - xxSum) / 2
        for ((key, value) in list) {
            if (key[0] == key[1]) {
                val cell = comb[i][xx]
                scoreSum += value * cell
                xx++
            } else {
                val cell = if (xy < 2) {
                    10
                } else if (xy > 2) {
                    -10
                } else {
                    xy3
                }
                scoreSum += cell * value
                xy++
            }
        }
        result = max(result, scoreSum / (N * (N - 1) / 2))
    }
    println(result)
}