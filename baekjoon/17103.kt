package `17103`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    val sieve = Array<Boolean>(1000001) { true }
    sieve[1] = false
    for (i in 2..1000000) {
        if (sieve[i]) {
            for (j in i + i..1000000 step i) {
                sieve[j] = false
            }
        }
    }
    repeat(T) {
        val N = reader.readLine().toInt()
        var cnt = 0
        for (i in 2..N / 2) {
            if (sieve[i] && sieve[N - i]) {
                cnt++
            }
        }
        writer.write("${cnt}\n")
    }
    writer.flush()
}
    