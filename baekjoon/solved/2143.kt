package `2143`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun getAllSubArr(size: Int, source: List<Int>): Map<Int, Int> {
    val result = HashMap<Int, Int>()
    val prefixSum = Array<Int>(size + 1) { 0 }
    for (i in 1..size) {
        prefixSum[i] = prefixSum[i - 1] + source[i - 1]
    }
    for (i in 0 until size) {
        for (j in 0..i) {
            val cur = prefixSum[i + 1] - prefixSum[j]
            if (!result.containsKey(cur))
                result[cur] = 0
            result[cur] = result[cur]!! + 1
        }
    }
    return result
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    val n = reader.readLine().toInt()
    val A = reader.readLine().split(" ").map { it.toInt() }
    val m = reader.readLine().toInt()
    val B = reader.readLine().split(" ").map { it.toInt() }

    val aSubArr = getAllSubArr(n, A)
    val bSubArr = getAllSubArr(m, B)
    var result = 0L
    aSubArr.forEach { key, value ->
        if (bSubArr.containsKey(T - key))
            result += bSubArr[T - key]!! * value.toLong()
    }
    writer.write("${result}\n")
    writer.flush()
}