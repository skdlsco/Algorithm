package `1339`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()
    val lineArr = Array<String>(N) { reader.readLine() }

    val score = Array<Pair<Char, Int>>(26) { Pair(('A'.code + it).toChar(), 0) }

    // 점수를 매긴다.
    lineArr.forEach { line ->
        var point = 1
        line.reversed().forEach {
            val idx = it.code - 'A'.code
            val s = score[idx]
            score[idx] = Pair(s.first, s.second + point)
            point *= 10
        }
    }
    // 점수 가장 큰 알파벳 부터 9 ~ 0 순으로 배정한다.
    score.sortByDescending { it.second }
    score.forEachIndexed { num, pair ->
        if (num < 10)
            for (i in lineArr.indices) {
                lineArr[i] = lineArr[i].replace(pair.first, (9 - num).digitToChar())
            }
    }
    println(lineArr.sumOf { it.toInt() })
}