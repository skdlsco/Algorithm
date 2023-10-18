package `28135`

import java.io.BufferedReader
import java.io.InputStreamReader

fun has50(num: Int): Boolean {
    return num.toString().contains("50")
}

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val N = reader.readLine().toInt()

    var cnt = 0
    for (i in 1..N) {
        if (has50(i - 1))
            cnt++
        cnt++
    }
    println(cnt)
}