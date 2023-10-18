package `28339`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun calcPower(arr: Array<Array<Int>>): Int {
    var sum = 0
    for (i in 1..9) {
        var cur = -1
        for (power in 100 downTo 1) {
            if (arr[i][power] > 0) {
                cur = power
                break
            }
        }
        if (cur == -1)
            return -1
        sum += cur
    }
    return sum
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val (N, K) = reader.readLine().split(" ").map { it.toInt() }
        val arr = Array<Array<Int>>(10) { Array(101) { 0 } }
        val input = Array<Pair<Int, Int>>(N) {
            val (pos, power) = reader.readLine().split(" ").map { it.toInt() }
            Pair(pos, power)
        }
        for (i in 0 until K) {
            val (pos, power) = input[i]
            arr[pos][power]++
        }
        var ans = calcPower(arr)
        for (i in K until N) {
            val cur = input[i]
            val remove = input[i - K]
            arr[cur.first][cur.second]++
            arr[remove.first][remove.second]--
            ans = maxOf(ans, calcPower(arr))
        }
        writer.write("${ans}\n")
    }
    writer.flush()
}
    