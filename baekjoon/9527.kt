package `9527`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun getPrefixSum(sumArr: LongArray, target: Long): Long {
    var sum = 0L
    var num = target
    for (i in 63 downTo  1) {
        val bit = 1L shl i
        if (num and bit > 0) {
            sum += sumArr[i - 1] + (num - bit) + 1
            num -= bit
        }
    }
    if (num % 2L == 1L)
        sum += 1L
    return sum
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (A, B) = reader.readLine().split(" ").map { it.toLong() }

    val sumArr = LongArray(63)
    sumArr[0] = 1
    var num = 1L
    var i = 1
    while (num < B) {
        num = num shl 1
        sumArr[i] = sumArr[i - 1] * 2 + num
        i++
    }
    println(getPrefixSum(sumArr, B) - getPrefixSum(sumArr, A - 1))
}