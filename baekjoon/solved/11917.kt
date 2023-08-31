package `11917`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.TreeSet

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val S = reader.readLine().trim().split(" ").map { it.toInt() }
    val M = reader.readLine().toInt()
    val set = TreeSet<Int>()
    set.addAll(S)
    if (M <= N) {
        writer.write("${S[M - 1]}")
    } else {
        var max = S.maxOf { it }
        var ans = set.size
        for (i in N + 1 until M) {
            if (set.contains(set.size)) {
                break
            }
            if (set.size > max) {
                ans += M - i
                break
            }
            set.add(set.size)
            ans = set.size
        }
        writer.write("${ans}")
    }
    writer.flush()
}