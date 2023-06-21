package `15829`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val MOD = 1234567891

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    val L = reader.readLine().toInt()
    val str = reader.readLine()
    var r = 1L
    var result = 0L
    for (i in 0 until L) {
        result = (result + r * (str[i].code - 'a'.code + 1)) % MOD
        r = (r * 31) % MOD
    }
    writer.write("${result}\n")
    writer.flush()
}