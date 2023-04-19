package `2287`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.LinkedList

val dp = Array<MutableSet<Int>>(9) { HashSet() }
val queue = LinkedList<Int>()

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val K = reader.readLine().toInt()
    val n = reader.readLine().toInt()
    var num = 0
    for (i in 1..8) {
        num = num * 10 + K
        dp[i].add(num)
    }
    for (i in 1..7) {
        for (j in 1..8 - i) {
            dp[i].forEach { a ->
                dp[j].forEach { b ->
                    dp[i + j].add(a + b)
                    dp[i + j].add(a - b)
                    dp[i + j].add(b - a)
                    dp[i + j].add(a * b)
                    if (b != 0)
                        dp[i + j].add(a / b)
                    if (a != 0)
                        dp[i + j].add(b / a)
                }
            }
        }
    }
    repeat(n) {
        val target = reader.readLine().toInt()
        for (i in 0..8) {
            if (dp[i].contains(target)) {
                writer.write("${i}\n")
                return@repeat
            }
        }
        writer.write("NO\n")
    }
    writer.flush()
}