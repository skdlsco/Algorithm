package `29792`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
    val spec = Array<Long>(N) { reader.readLine().toLong() }.sortedByDescending { it }
    val bosses = Array<Pair<Long, Long>>(K) { Pair(0L, 0L) }
    repeat(K) {
        val (P, Q) = reader.readLine().split(" ").map { it.toLong() }
        bosses[it] = Pair(P, Q)
    }
    var total = 0L
    for (i in 0 until M) {
        val damage = spec[i]
        var maxValue = 0L
        for (bit in 0 until (1 shl K)) {
            var time = 900L
            var sum = 0L
            for (j in 0 until K) {
                if (bit and (1 shl j) > 0) {
                    val boss = bosses[j]
                    val used = boss.first / damage + if (boss.first % damage != 0L) 1 else 0
                    if (used <= time) {
                        time -= used
                        sum += boss.second
                    }
                }
            }
            maxValue = maxOf(maxValue, sum)
        }
        total += maxValue
    }
    writer.write("${total}\n")
    writer.flush()
}
    