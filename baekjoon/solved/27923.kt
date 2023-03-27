package `27923`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K, L) = reader.readLine().split(" ").map { it.toInt() }
    val burger = reader.readLine().split(" ").map { it.toLong() }.sortedDescending()
    val drinkCoke = Array<Int>(N) { 0 }
    reader.readLine().split(" ").map { drinkCoke[it.toInt() - 1]++ }
    val cokePower = Array<Int>(N) { it }
    var coke = 0
    for (i in 0 until N) {
        if (i >= L)
            coke -= drinkCoke[i - L]
        coke += drinkCoke[i]
        cokePower[i] = coke
    }
    val cokeIdxRank = Array<Int>(N) { it }.sortedByDescending {
        cokePower[it]
    }
    val sequence = Array<Int>(N) { it }
    for (i in 0 until N) {
        sequence[cokeIdxRank[i]] = i
    }
    var result = 0L
    var cur = 0L
    for (i in 0 until N) {
        val nowCokePower = cokePower[i]
        val burgerWeight = burger[sequence[i]]
        if (nowCokePower < 64)
            cur += burgerWeight / (1L shl nowCokePower)
        result = max(result, cur)
    }
    writer.write("${result}")
    writer.flush()
}