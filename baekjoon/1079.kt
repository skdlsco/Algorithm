package `1079`

import kotlin.math.max

var player = 0
var N = 0
val dead = BooleanArray(16) { false }
val score = IntArray(16) { 0 }
val board = Array<IntArray>(16) { IntArray(16) { 0 } }
var result = 0
var flag = false

fun bruteforce(cur: Int, cnt: Int) {
    if (cur == 1 || dead[player]) {
        result = max(result, cnt)
        if (cur == 1)
            flag = true
        return
    }
    if (flag)
        return
    if (cur % 2 == 1) {
        var target = -1
        for (next in 0 until N) {
            if (dead[next])
                continue
            if (target == -1)
                target = next
            if (score[next] > score[target])
                target = next
        }
        dead[target] = true
        bruteforce(cur - 1, cnt)
        dead[target] = false
    } else {
        for (next in 0 until N) {
            if (dead[next] || next == player)
                continue
            dead[next] = true
            for (i in 0 until N) {
                score[i] += board[next][i]
            }
            bruteforce(cur - 1, cnt + 1)
            dead[next] = false
            for (i in 0 until N) {
                score[i] -= board[next][i]
            }
        }
    }
}

fun main() {
    N = readLine()!!.toInt()
    readLine()!!.split(" ").map { it.toInt() }.forEachIndexed { index, i ->
        score[index] = i
    }

    for (y in 0 until N) {
        val row = readLine()!!.split(" ").map { it.toInt() }
        for (x in 0 until N) {
            board[y][x] = row[x]
        }
    }
    player = readLine()!!.toInt()
    bruteforce(N, 0)

    println(result)
}