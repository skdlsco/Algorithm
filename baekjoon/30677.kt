package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K, C, R) = reader.readLine().split(" ").map { it.toLong() }
    val base = reader.readLine().split(" ").map { it.toLong() }
    val s = reader.readLine().split(" ").map { it.toLong() }
    val p = reader.readLine().split(" ").map { it.toLong() }
    val cnt = Array<Long>(K.toInt()) { 0L }
    var sum = 0L
    var stress = 0L
    var combo = 0L
    for (i in 0 until N) {
        val magic = reader.readLine().toInt() - 1
        if (magic == -1) {
            combo = 0
            stress = maxOf(0, stress - R)
        } else {
            sum += (base[magic] * (100 + combo * C) * (100 + s[magic] * cnt[magic])) / 10000
            combo++
            cnt[magic]++
            stress += p[magic]
        }
        if (stress > 100) {
            sum = -1
            break
        }
    }
    writer.write("${sum}\n")
    writer.flush()
}
    