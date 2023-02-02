package `2407`

import java.io.BufferedReader
import java.io.InputStreamReader
import java.math.BigInteger

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (n, m) = reader.readLine().split(" ").map { it.toInt() }
    var num = BigInteger.valueOf(1L)

    ((n - m + 1)..n).forEach {
        num = num.multiply(BigInteger.valueOf(it.toLong()))
    }
    (1..m).forEach {
        num = num.divide(BigInteger.valueOf(it.toLong()))
    }
    println(num.toString())
}