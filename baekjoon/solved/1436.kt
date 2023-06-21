package `1436`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var N = reader.readLine().toInt()
    var num = 0
    while (true) {
        var temp = num
        var has666 = false
        while (temp > 0) {
            if (temp % 1000 == 666) {
                has666 = true
                break
            }
            temp /= 10
        }
        if (has666)
            N--
        if (N == 0)
            break
        num++
    }
    writer.write("${num}")
    writer.flush()
}