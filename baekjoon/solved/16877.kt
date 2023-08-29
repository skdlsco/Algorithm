package `16877`

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

val dp = Array<Int>(3000001) { 0 }
val fibo = Array<Int>(40) { 1 }

fun mex(i: Int): Int {
    val c = BooleanArray(41) { false }
    for (f in fibo) {
        if (i - f >= 0)
            c[dp[i - f]] = true
    }
    var ret = 0
    while (c[ret])
        ret++
    return ret
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))
    fibo[1] = 1
    for (i in 2 until 40) {
        fibo[i] = fibo[i - 1] + fibo[i - 2]
    }
    for (i in 1 until 3000001) {
        dp[i] = mex(i)
    }
    val N = reader.readLine().toInt()
    val games = reader.readLine().split(" ").map { it.toInt() }
    var result = dp[games[0]]
    for (i in 1 until N) {
        result = result xor dp[games[i]]
    }
    if (result == 0)
        writer.write("cubelover\n")
    else
        writer.write("koosaga\n")
    writer.flush()
}