package `Main`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var N = reader.readLine().toLong()
    val primes = ArrayList<Long>()
    val sieve = Array<Boolean>(1000001) { true }
    sieve[1] = false
    for (i in 2..1000000) {
        if (sieve[i]) {
            primes.add(i.toLong())
            for (j in i + i..1000000 step i) {
                sieve[j] = false
            }
        }
    }
    val divisor = ArrayList<Long>()
    primes.forEach {
        while (N > 1 && N % it == 0L) {
            divisor.add(it)
            N /= it
        }
    }
    if (N != 1L)
        divisor.add(N)
    if (divisor.size <= 1)
        writer.write("-1\n")
    else {
        val ans = ArrayList<Long>()
        var i = 0
        while (i < divisor.size) {
            var num = 1L
            if (i + 1 < divisor.size) {
                num *= divisor[i++]
                num *= divisor[i++]
                ans.add(num)
            } else {
                ans[ans.lastIndex] *= divisor[i++]
            }
        }
        writer.write(ans.joinToString(" "))
    }
    writer.flush()
}