package `1389`

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val M = scanner.nextInt()
    val info = Array<Array<Int>>(N) { Array(N) { Int.MAX_VALUE } }
    val kb = Array<Int>(N) { 0 }

    repeat(N) {
        info[it][it] = 0
    }

    repeat(M) {
        val start = scanner.nextInt() - 1
        val end = scanner.nextInt() - 1

        info[start][end] = 1
        info[end][start] = 1
    }

    repeat(N) { mid ->
        repeat(N) { start ->
            repeat(N) { end ->

                if (info[start][mid] != Int.MAX_VALUE && info[mid][end] != Int.MAX_VALUE && info[start][end] > info[start][mid] + info[mid][end]) {
                    info[start][end] = info[start][mid] + info[mid][end]
                }
            }
        }
    }
    repeat(N) { start ->
        repeat(N) { end ->
            kb[start] += info[start][end]
        }
    }
    val minValue = kb.minOrNull()
    println(kb.indexOf(minValue!!) + 1)
}