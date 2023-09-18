package `30023`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.min

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val N = reader.readLine().toInt()
    val input = reader.readLine()
    var ans = -1
    for (i in 0 until 3) {
        val arr = Array<Int>(N) { "RGB".indexOf(input[it]) }
        val color = (arr[0] + i) % 3
        var sum = 0
        for (j in 0..N - 3) {
            var change = 0
            while (color != arr[j]) {
                arr[j] = (arr[j] + 1) % 3
                arr[j + 1] = (arr[j + 1] + 1) % 3
                arr[j + 2] = (arr[j + 2] + 1) % 3
                change++
            }
            sum += change
        }
        if (arr[N - 1] == color && arr[N - 2] == color) {
            if (ans == -1)
                ans = sum
            else
                ans = min(ans, sum)
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}