package `4658`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun find(arr: Array<Array<Int>>, depth: Int, first: Int, prev: Int, selected: Array<Boolean>, sum: Int): Int {
    if (depth == 6) {
        return if (first == prev) {
            sum
        } else {
            -1
        }
    }
    var ans = -1
    for (i in 1 until 6) {
        if (selected[i])
            continue
        for (j in 0 until 3) {
            if (arr[i][j] == prev) {
                selected[i] = true
                ans = max(ans, find(arr, depth + 1, first, arr[i][(j + 1) % 3], selected, sum + arr[i][(j + 2) % 3]))
                selected[i] = false
            }
        }
    }
    return ans
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    while (true) {
        val arr = Array<Array<Int>>(6) { Array(3) { 0 } }
        repeat(6) {
            arr[it] = reader.readLine().split(" ").map { it.toInt() }.toTypedArray()
        }
        val ans = (0 until 3).maxOf {
            find(
                arr,
                1,
                arr[0][it],
                arr[0][(it + 1) % 3],
                Array(6) { it == 0 },
                arr[0][(it + 2) % 3]
            )
        }
        if (ans < 0)
            writer.write("none\n")
        else
            writer.write("${ans}\n")
        val symbol = reader.readLine()
        if (symbol == "$")
            break
    }
    writer.flush()
}
    