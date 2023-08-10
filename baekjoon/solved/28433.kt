package `28433`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun solve(N: Int, arr: List<Long>): Boolean {
    var cnt = 0
    var sum = 0L
    for (cur in arr) {
        if (cur > 0) {
            if (sum > 0) {
                cnt++
                sum = cur
            } else if (sum + cur > 0) {
                sum += cur
            } else {
                if (sum < 0)
                    cnt--
                sum = cur
            }
        }
        if (cur < 0) {
            if (sum < 0) {
                sum += cur
            } else if (sum + cur > 0) {
                sum += cur
            } else {
                if (sum > 0)
                    cnt++
                sum = cur
            }
        }
    }
    if (sum > 0)
        cnt++
    if (sum < 0)
        cnt--
    return cnt > 0
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val N = reader.readLine().toInt()
        val arr = reader.readLine().split(" ").map { it.toLong() }
        val ans = solve(N, arr)
        if (ans)
            writer.write("YES\n")
        else
            writer.write("NO\n")
    }
    writer.flush()
}