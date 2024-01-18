package `A2`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val (N, K) = reader.readLine().split(" ").map { it.toInt() }
    val arr = reader.readLine().split(" ").map { it.toInt() }
    var prev = -1
    var cnt = 0
    var able = true
    for (a in arr) {
        if (prev >= a) {
            if (prev >= a + K) {
                able = false
                break
            }
            cnt++
            prev = a + K
        } else {
            prev = a
        }
    }
    if (able)
        writer.write("${cnt}\n")
    else
        writer.write("${-1}\n")
    writer.flush()
}
    