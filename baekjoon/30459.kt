package `F`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.abs

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, M, R) = reader.readLine().split(" ").map { it.toInt() }
    val posArr = reader.readLine().split(" ").map { it.toLong() }.sorted()
    val lenArr = reader.readLine().split(" ").map { it.toLong() }.sorted()
    var ans = -1L
    for (i in 0 until N) {
        for (j in i + 1 until N) {
            val bottom = abs(posArr[i] - posArr[j])
            var left = 0
            var right = M - 1
            while (left < right) {
                val mid = (left + right) / 2
                val height = lenArr[mid]
                if (height * bottom <= R * 2L) {
                    ans = maxOf(ans, height * bottom)
                    left = mid + 1
                } else {
                    right = mid
                }
            }
            if (left in 0 until M) {
                val height = lenArr[left]
                if (height * bottom <= R * 2L)
                    ans = maxOf(ans, height * bottom)
            }
        }
    }
    if (ans == -1L)
        writer.write("-1")
    else {
        writer.write("${ans / 2}")
        writer.write(".")
        if (ans % 2 == 0L)
            writer.write("0")
        else
            writer.write("5")
    }
    writer.flush()
}
    