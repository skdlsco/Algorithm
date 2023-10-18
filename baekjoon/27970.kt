package `27970`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

const val MOD = 1000000007
fun fastPower(x: Int): Long {
    if (x == 0)
        return 1
    else if (x == 1)
        return 2
    val y = fastPower(x / 2) % MOD
    if (x % 2 == 1)
        return (((y * y) % MOD) * 2)
    return (y * y) % MOD
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    var result = 0L
    val line = reader.readLine()
    for (i in line.indices) {
        if (line[i] == 'O') {
            result += fastPower(i)
            result %= MOD
        }
    }
    writer.write("${result}\n")
    writer.flush()
}