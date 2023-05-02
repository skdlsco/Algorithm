package `2014`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.util.PriorityQueue
import java.util.TreeSet
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (K, N) = reader.readLine().split(" ").map { it.toLong() }
    val numList = PriorityQueue<Long>()
    val numSet = TreeSet<Long>()
    val primes = reader.readLine().split(" ").map { it.toLong() }
    var maxValue = 0L
    primes.forEach {
        maxValue = max(maxValue, it)
        numList.add(it)
    }
    for (i in 1 until N) {
        val cur = numList.remove()
        for (prime in primes) {
            val value = cur * prime
            if (numList.size > N - i && cur * prime >= maxValue)
                break
            if (numSet.contains(value))
                continue
            numList.add(value)
            numSet.add(value)
            maxValue = max(maxValue, value)
        }
    }
    writer.write("${numList.remove()}\n")
    writer.flush()
}