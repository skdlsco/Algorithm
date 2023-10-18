package `11054`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    val lis = Array<Int>(N) { 1 }
    val reverseLis = Array<Int>(N) { 1 }

    for (i in 1 until N) {
        for (j in 0 until i) {
            if (arr[j] < arr[i])
                lis[i] = max(lis[i], lis[j] + 1)
        }
    }
    for (i in N - 1 downTo 0) {
        for (j in i until N) {
            if (arr[j] < arr[i])
                reverseLis[i] = max(reverseLis[i], reverseLis[j] + 1)
        }
    }
    writer.write("${(0 until N).maxOf { lis[it] + reverseLis[it] - 1 }}\n")
    writer.flush()
}