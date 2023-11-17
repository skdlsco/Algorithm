package `J`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val arr = Array<Long>(1024) { 0 }
    repeat(N) {
        val line = reader.readLine()
        var bit = 0
        line.forEach {
            bit = bit or (1 shl (it.code - '0'.code))
        }
        arr[bit]++
    }
    var ans = 0L
    for (i in 1 until 1024) {
        for (j in i until 1024) {
            val bit = i or j
            if (K == bit.countOneBits()) {
                if (i != j)
                    ans += arr[i] * arr[j]
                else
                    ans += arr[i] * (arr[j] - 1) / 2
            }
        }
    }
    writer.write("${ans}\n")
    writer.flush()
}
    