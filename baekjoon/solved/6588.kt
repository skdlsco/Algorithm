package `6588`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.`out`))

    val sieve = BooleanArray(1000001) { true }
    sieve[0] = false
    sieve[1] = false
    val primeArr = ArrayList<Int>()

    for (i in 2..1000) {
        if (sieve[i])
            (i + i until 1000001 step i).forEach {
                sieve[it] = false
            }

    }
    sieve.forEachIndexed { index, b ->
        if (b)
            primeArr.add(index)
    }
    while (true) {
        val num = reader.readLine().toInt()
        if (num == 0)
            break
        val result = primeArr.any { a ->
            val diff = num - a
            if (sieve[diff]) {
                writer.write("$num = $a + $diff\n")
                true
            } else
                false
        }
        if (!result) {
            writer.write("Goldbach's conjecture is wrong.\n")
        }
    }
    writer.flush()
    writer.close()
}