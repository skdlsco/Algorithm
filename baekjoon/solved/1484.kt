package `1484`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val G = reader.readLine().toInt()
    var a = 1
    var b = 1
    var isExist = false
    while (b <= G) {
        val result = a * a - b * b
        if (result < G) {
            a++
        } else if (result == G){
            writer.write("${a}\n")
            isExist = true
            a++
        } else {
            b++
        }
    }
    if (!isExist)
        writer.write("-1\n")
    writer.flush()
}