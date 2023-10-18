import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun gcd(a: Long, b: Long): Long {
    if (b == 0L)
        return a
    return gcd(b, a % b)
}

fun getPrimes(n: Int): List<Int> {
    val sieve = Array<Boolean>(n + 1) { true }
    val primes = ArrayList<Int>()
    for (i in 2..n) {
        if (sieve[i]) {
            primes.add(i)
            for (j in i + i..n step i) {
                sieve[j] = false
            }
        }
    }
    return primes
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    val primes = getPrimes(63246)
    repeat(T) {
        var N = reader.readLine().toLong()
        N = maxOf(N, 2L)
        while (true) {
            var isPrime = true
            for (prime in primes) {
                if (N == prime.toLong())
                    break
                if (N % prime == 0L) {
                    isPrime = false
                    break
                }
            }
            if (isPrime)
                break
            N++
        }
        writer.write("${N}\n")
    }
    writer.flush()
}