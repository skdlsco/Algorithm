package `1912`

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
    var sum = arr[0]
    var result = arr[0]
    for (i in 1 until N) {
        if (arr[i] + sum < arr[i])
            sum = arr[i]
        else
            sum += arr[i]
        result = max(result, sum)
    }
    writer.write("${result}\n")
    writer.flush()
}