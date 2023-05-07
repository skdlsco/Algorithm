package `12430`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.StringTokenizer

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

val MAX_VALUE = 101000

fun solution(N: Int, arr: Array<Pair<Int, Int>>): Int {
    arr.sortBy {
        it.second + it.first
    }
    // i 일째에 먹을 일이 있는가?
    val dp = Array<Boolean>(MAX_VALUE + 1) { false }
    dp[0] = true
    for (i in 0 until N) {
        val (P, S) = arr[i]
        for (j in P downTo 0) {
            if (dp[j])
                dp[j + S] = true
        }
    }
    return dp.indexOfLast { it }
}

fun main() {
    val T = reader.readLine().toInt()
    repeat(T) {
        val N = reader.readLine().toInt()
        val arr = Array<Pair<Int, Int>>(N) {
            val tokenizer = StringTokenizer(reader.readLine())
            Pair(tokenizer.nextToken().toInt(), tokenizer.nextToken().toInt())
        }
        writer.write("Case #${it + 1}: ${solution(N, arr)}\n")
    }
    writer.flush()
}