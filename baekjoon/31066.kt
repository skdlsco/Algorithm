package `A`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val T = reader.readLine().toInt()
    repeat(T) {
        var (N, M, K) = reader.readLine().split(" ").map { it.toInt() }
        if (K == 1 && M == 1 && N > 1) {
            writer.write("${-1}\n")
        } else {
            var cnt = 0
            var a = N
            var c = 0
            while (c != N) {
                if (cnt % 2 == 0) {
                    val pCnt = minOf(K * M, a)
                    a -= pCnt
                    c += pCnt
                } else {
                    a += 1
                    c -= 1
                }
                cnt++
            }
            writer.write("${cnt}\n")
        }
    }
    writer.flush()
}
    