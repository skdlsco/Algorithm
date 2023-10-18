package `1016`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (min, max) = reader.readLine().split(" ").map { it.toLong() }
    val sieve = Array<Boolean>(1000001) { true }

    var i = 2L
    while (i * i <= max) {
        val k = i * i
        val start = min / k + if (min % k == 0L) 0 else 1
        val end = max / k
        for (j in (start * k)..(end * k) step k) {
            sieve[(j - min).toInt()] = false
        }
        i++
    }
    var cnt = 0
    sieve.forEachIndexed { index, b ->
        if (index < max - min + 1) {
            if (b)
                cnt++
        }
    }
    println(cnt)
}