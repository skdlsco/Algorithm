package `22238`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    var targetX = 0L
    var targetY = 0L
    val target = Array<Long>(N) { 0 }
    repeat(N) {
        val (x, y, hp) = reader.readLine().split(" ").map { it.toLong() }
        target[it] = hp
        targetX = x
        targetY = y
    }
    var totalDamage = 0L
    target.sort()
    repeat(M) {
        val (x, y, d) = reader.readLine().split(" ").map { it.toLong() }
        if (targetX * y == x * targetY &&
            targetX.compareTo(0L) == x.compareTo(0L) &&
            targetY.compareTo(0L) == y.compareTo(0L)
        )
            totalDamage += d
        var left = 0
        var right = N
        while (left < right) {
            val mid = (left + right) / 2
            if (target[mid] <= totalDamage)
                left = mid + 1
            else
                right = mid
        }
        writer.write("${N - right}\n")
    }
    writer.flush()
}
    