package `29717`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val N = reader.readLine().toLong()
        val exp = N * (N + 1) / 2
        var left = 0L
        var right = 1000000000L
        while (left < right) {
            val mid = (left + right) / 2L
            val need = mid * (4 + (mid - 1) * 2) / 2
            val next = need + (mid + 1) * 2
            if (exp in need until next) {
                left = mid
                break
            } else if (exp < need)
                right = mid - 1
            else
                left = mid + 1
        }
        writer.write("${left + 1}\n")
    }
    writer.flush()
}
    