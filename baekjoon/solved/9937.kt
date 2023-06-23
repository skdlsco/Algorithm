package `9937`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.absoluteValue

data class Data(val a: Long, val b: Long, val isMinus: Boolean)

const val MOD = 1000000007L

fun gcd(a: Long, b: Long): Long {
    if (b == 0L)
        return a
    return gcd(b, a % b)
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toLong()
    val map = HashMap<Data, Long>(N.toInt())

    repeat(N.toInt()) {
        val (A, B, C) = reader.readLine().split(" ").map { it.toLong() }
        val isMinus = (A < 0) xor (B < 0)
        val gcd = gcd(A, B)
        val data = Data((A / gcd).absoluteValue, (B / gcd).absoluteValue, isMinus)
        if (!map.containsKey(data))
            map[data] = 0
        map[data] = map[data]!! + 1
    }
    var result = (N * (N - 1) * (N - 2) / 6) % MOD
    for ((d, cnt) in map) {
        if (cnt >= 2)
            result -= (cnt * (cnt - 1) * (N - cnt) / 2) % MOD
        if (cnt >= 3)
            result -= (cnt * (cnt - 1) * (cnt - 2) / 6) % MOD
        result %= MOD
    }
    if (result < 0)
        result += MOD
    writer.write("${result}\n")
    writer.flush()
}