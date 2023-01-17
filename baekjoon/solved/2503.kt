package baekjoon.solved

import java.util.*

data class Baseball(val num: String, val s: Int, val b: Int) {
    fun isCorrect(answer: String): Boolean {
        val S = answer.mapIndexed { index, c ->
            num[index] == c
        }.count { it }
        val B = answer.mapIndexed { index, c ->
            num[index] != c && c in num
        }.count { it }
        return s == S && b == B
    }
}

fun makeBall(baseBall: Array<Baseball>, num: CharArray, idx: Int): Int {
    var cnt = 0
    if (idx == 3) {
        return if (baseBall.all { it.isCorrect(num.joinToString("")) }) 1 else 0
    }
    "123456789".forEach {
        if (it !in num.slice(0..idx)) {
            num[idx] = it
            cnt += makeBall(baseBall, num, idx + 1)
        }
    }
    return (cnt)
}

fun main() {
    val scanner = Scanner(System.`in`)
    val N = scanner.nextInt()
    val baseBall = Array<Baseball>(N) { Baseball("", 0, 0) }

    repeat(N) {
        baseBall[it] = Baseball(scanner.next(), scanner.nextInt(), scanner.nextInt())
    }
    println(makeBall(baseBall, CharArray(3), 0))
}