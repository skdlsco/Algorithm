package `A`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, L) = reader.readLine().split(" ").map { it.toInt() }
    var ans = 0
    var ansCnt = 0
    repeat(N) {
        val line = reader.readLine()
        var cnt = 0
        var idx = 0
        while (idx < L) {
            if (line[idx] == '1') {
                cnt++
                while (idx < L && line[idx] == '1')
                    idx++
            } else
                idx++
        }
        if (cnt > ans) {
            ans = cnt
            ansCnt = 1
        } else if (cnt == ans)
            ansCnt++
    }
    writer.write("${ans} ${ansCnt}\n")
    writer.flush()
}
    