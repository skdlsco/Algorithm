package `10807`

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))

    val N = reader.readLine().toInt()
    val arr = reader.readLine().split(" ").map { it.toInt() }
    val v = reader.readLine().toInt()
    var cnt = 0
    for (n in arr) {
        if (v == n)
            cnt++
    }
    println(cnt)
}