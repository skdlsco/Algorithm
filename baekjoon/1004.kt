package `1004`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.pow
import kotlin.math.sqrt


fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        val (start, end) = reader.readLine().split(" ").map { it.toDouble() }.chunked(2)
        val n = reader.readLine().toInt()
        var cnt = 0
        repeat(n) {
            val (x, y, r) = reader.readLine().split(" ").map { it.toDouble() }
            val startD = sqrt((start[0] - x).pow(2) + (start[1] - y).pow(2))
            val endD = sqrt((end[0] - x).pow(2) + (end[1] - y).pow(2))
            if ((startD < r && endD > r) || (startD > r && endD < r))
                cnt++
        }
        writer.write("${cnt}\n")
    }
    writer.flush()
    writer.close()
    reader.close()
}
