package `15658`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max
import kotlin.math.min

var minResult = Int.MAX_VALUE
var maxResult = Int.MIN_VALUE


fun bruteforce(N: Int, depth: Int, arr: Array<Int>, plus: Int, minus: Int, multiply: Int, div: Int, num: Int) {
    if (depth == N) {
        minResult = min(minResult, num)
        maxResult = max(maxResult, num)
        return
    }
    if (plus > 0)
        bruteforce(N, depth + 1, arr, plus - 1, minus, multiply, div, num + arr[depth])
    if (minus > 0)
        bruteforce(N, depth + 1, arr, plus, minus - 1, multiply, div, num - arr[depth])
    if (multiply > 0)
        bruteforce(N, depth + 1, arr, plus, minus, multiply - 1, div, num * arr[depth])
    if (div > 0)
        bruteforce(N, depth + 1, arr, plus, minus, multiply, div - 1, num / arr[depth])
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
    val (plus, minus, mul, div) = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()

    bruteforce(N, 1, arr, plus, minus, mul, div, arr[0])
    writer.write("${maxResult}\n${minResult}\n")
    writer.flush()
}