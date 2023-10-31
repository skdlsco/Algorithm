package `D`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Int>(M + 1) { 0 }
    repeat(N) {
        val row = reader.readLine().split(" ").map { it.toInt() }
        arr[row[1]]++
        arr[row.last()]++
    }
    var left = arr[1]
    var right = N * 2 - arr[1]
    var sum = 0L
    for (i in 1..M) {
        sum += arr[i] * (i - 1)
    }
    var minSum = sum
    var ans = 1
    for (i in 2..M) {
        sum += left
        sum -= right
        if (sum < minSum) {
            ans = i
            minSum = sum
        }
        left += arr[i]
        right -= arr[i]
    }
    writer.write("${ans}\n")
    writer.flush()
}
    