package `1205`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val (N, num, P) = reader.readLine().split(" ").map { it.toInt() }
    if (N == 0) {
        println(1)
        return
    }
    val arr = reader.readLine().split(" ").map { it.toInt() }.sortedDescending()
    if (N >= P && arr[min(N, P) - 1] >= num) {
        println(-1)
    } else {
        var idx = 0
        while (idx < N && arr[idx] > num)
            idx++
        println("${idx + 1}\n")
    }
}
    