package `1644`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.sqrt

fun getPrimeList(N: Int): List<Int> {
    val sieve = Array<Boolean>(N + 1) { true }
    val primeList = ArrayList<Int>()
    for (i in 2..sqrt(N.toDouble()).toInt()) {
        if (sieve[i]) {
            for (j in i + i..N step i)
                sieve[j] = false
        }
    }
    for (i in 2..N) {
        if (sieve[i])
            primeList.add(i)
    }
    return primeList
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val primeList = getPrimeList(N)
    var cnt = 0
    var sum = 0
    var start = 0
    for (prime in primeList) {
        sum += prime
        while (sum > N) {
            sum -= primeList[start]
            start++
        }
        if (sum == N)
            cnt++
    }
    writer.write("${cnt}\n")
    writer.flush()
}